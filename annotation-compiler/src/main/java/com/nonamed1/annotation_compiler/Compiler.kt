package com.nonamed1.annotation_compiler

import com.nonamed1.annotation.ViewHolder
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.TypeSpec
import java.io.File
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.TypeElement

class Compiler : AbstractProcessor() {
    override fun process(p0: MutableSet<out TypeElement>?, p1: RoundEnvironment?): Boolean {
        p1?.let {
            it.getElementsAnnotatedWith(ViewHolder::class.java).forEach {
                createClass(it)
            }
        }
        return true
    }

    private fun createClass(element: Element) {
        val packageName = processingEnv.elementUtils.getPackageOf(element).toString()
        val itemName = element.simpleName.toString()

        FileSpec.builder(packageName, itemName)
            .addType(ViewHolderClassBuilder().build(element, packageName))
            .build()
            .writeTo(File(processingEnv.options["kapt.kotlin.generated"]))
    }


    // target anotations
    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        return mutableSetOf<String>().apply {
            add(ViewHolder::class.java.canonicalName)
        }
    }

    // annotation processor를 적용할 jdk version
    override fun getSupportedSourceVersion(): SourceVersion {
        return SourceVersion.latestSupported()
    }
}