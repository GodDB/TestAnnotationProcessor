package com.nonamed1.annotation_compiler

import com.nonamed1.annotation.ViewHolder
import com.squareup.kotlinpoet.*
import javax.lang.model.element.Element
import javax.lang.model.type.TypeMirror

class ViewHolderClassBuilder {

    fun build(element: Element, packageName: String): TypeSpec {
        val annotation = element.getAnnotation(ViewHolder::class.java)
        val name = element.simpleName.toString() + "ViewHolder"
        val layoutId = annotation.layoutId

        return TypeSpec.classBuilder(name)
            .buildFactoryMethod(layoutId, name, packageName)
            .buildConstructor()
            .buildSuperClass()
            .buildField()
            .buildBindMethod(element.asType())
            .build()
    }
}

private fun TypeSpec.Builder.buildFactoryMethod(layoutId: Int, viewholderName: String, packageName: String): TypeSpec.Builder {
    return this.addFunction(
        FunSpec.builder("create")
            .addModifiers(KModifier.PUBLIC)
            .returns(ClassName(packageName, viewholderName))
            .addParameter("parent", ClassName("android.view", "ViewGroup"))
            .addStatement("val binding = androidx.databinding.DataBindingUtil.inflate<androidx.databinding.ViewDataBinding>(android.view.LayoutInflater.from(parent.context), $layoutId, parent, false)")
            .addStatement("return $viewholderName(binding)")
            .build()
    )
}

private fun TypeSpec.Builder.buildConstructor(): TypeSpec.Builder {
    return this.primaryConstructor(
        FunSpec.constructorBuilder()
            .addParameter(ParameterSpec("binding", ClassName("androidx.databinding", "ViewDataBinding")))
            .build()
    )
}

private fun TypeSpec.Builder.buildSuperClass(): TypeSpec.Builder {
    return this.superclass(ClassName("androidx.recyclerview.widget.RecyclerView", "ViewHolder"))
        .addSuperclassConstructorParameter("binding.root")
}

private fun TypeSpec.Builder.buildField(): TypeSpec.Builder {
    return this.addProperty("binding", ClassName("androidx.databinding", "ViewDataBinding"), KModifier.PRIVATE)
        .addInitializerBlock(CodeBlock.of("this.binding = binding"))
}

private fun TypeSpec.Builder.buildBindMethod(type: TypeMirror): TypeSpec.Builder {
    return this.addFunction(
        FunSpec.builder("bind")
            .addModifiers(KModifier.PUBLIC)
            .addParameter("data", type.asTypeName())
            .addStatement("binding.setVariable(BR.Data, data)")
            .addStatement("binding.executePendingBindings()")
            .build()
    )
}