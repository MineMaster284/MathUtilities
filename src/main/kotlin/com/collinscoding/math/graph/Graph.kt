package com.collinscoding.math.graph

open class Graph<E: Any> {
    val edges: EdgeSet<E> = EdgeSet()
    val nodes: MutableSet<E> = mutableSetOf()

    /** Gets all edges coming from a node. */
    fun getEdgesFrom(node: E): EdgeSet<E> = edges.filter { it.from == node }.toEdgeSet()
    /** Gets all edges going to a node. */
    fun getEdgesTo(node: E): EdgeSet<E> = edges.filter { it.to == node }.toEdgeSet()

    /** Adds an edge to the graph. */
    fun addEdge(from: E, to: E) {
        addNode(from)
        addNode(to)
        edges.add(Edge(from, to))
    }
    /** Adds an edge to the graph. */
    fun addEdge(edge: Edge<E>) {
        addNode(edge.from)
        addNode(edge.to)
        edges.add(edge)
    }
    /** Adds a node to the graph. */
    fun addNode(node: E) {
        nodes.add(node)
    }

    /** Removes an edge from the graph. */
    fun removeEdge(from: E, to: E) {
        edges.removeAll { it.from == from && it.to == to }
    }
    /** Removes an edge from the graph. */
    fun removeEdge(edge: Edge<E>) {
        edges.remove(edge)
    }
    /** Removes a node from the graph. */
    fun removeNode(node: E) {
        nodes.remove(node)
        edges.removeAll { it.from == node || it.to == node }
    }
}

open class InfoGraph<E: Any> {
    val edges: InfoEdgeSet<E> = InfoEdgeSet()
    val nodes: MutableSet<E> = mutableSetOf()

    /** Gets all edges coming from a node. */
    fun getEdgesFrom(node: E): InfoEdgeSet<E> = edges.filter { it.from == node }.toInfoEdgeSet()
    /** Gets all edges going to a node. */
    fun getEdgesTo(node: E): InfoEdgeSet<E> = edges.filter { it.to == node }.toInfoEdgeSet()

    /** Adds an edge to the graph with data. */
    fun addEdge(from: E, to: E, data: Map<String, String>) = edges.add(InfoEdge(from, to, data))
    /** Adds a node to the graph. */
    fun addNode(node: E) {
        if (!nodes.contains(node)) nodes.add(node)
    }
}
