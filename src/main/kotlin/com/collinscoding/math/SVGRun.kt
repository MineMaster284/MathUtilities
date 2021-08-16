package com.collinscoding.math

import org.w3c.dom.Document
import org.w3c.dom.NodeList
import java.io.File
import javax.xml.parsers.DocumentBuilderFactory

fun readXml(filename: String): Document = readXml(File(filename))
fun readXml(xmlFile: File): Document {
    val dbFactory = DocumentBuilderFactory.newInstance()
    val dBuilder = dbFactory.newDocumentBuilder()
    val xmlInput = xmlFile.inputStream()

    return dBuilder.parse(xmlInput)
}

fun examinePaths(filename: String) = examinePaths(readXml(filename))
fun examinePaths(xmlFile: File) = examinePaths(readXml(xmlFile))
fun examinePaths(doc: Document): List<String> {
    val elementNodeList = doc.getElementsByTagName("path") as NodeList

    return (0 until elementNodeList.length).map { elementIndex ->
        val element = elementNodeList.item(elementIndex)
        element.attributes.getNamedItem("d").nodeValue
    }
}

fun main() {
}
