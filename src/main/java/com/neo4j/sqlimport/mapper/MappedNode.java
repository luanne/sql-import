package com.neo4j.sqlimport.mapper;

/**
 * A node with its mapping to the schema
 * User: luanne
 * Date: 30/06/12
 * Time: 7:25 PM
 */
public class MappedNode {

    private String nodeType;
    private String indexName;
    private Table table;

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }
}
