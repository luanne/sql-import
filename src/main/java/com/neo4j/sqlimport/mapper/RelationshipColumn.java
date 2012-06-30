package com.neo4j.sqlimport.mapper;

/**
 * An RDBMS column that maps to a relationship
 * User: luanne
 * Date: 30/06/12
 * Time: 7:32 PM
 */
public class RelationshipColumn extends Column{

    private String relationshipType;
    private String isStartNode;
    private String columnNodeType;

    public String getRelationshipType() {
        return relationshipType;
    }

    public void setRelationshipType(String relationshipType) {
        this.relationshipType = relationshipType;
    }

    public String getStartNode() {
        return isStartNode;
    }

    public void setStartNode(String startNode) {
        isStartNode = startNode;
    }

    public String getColumnNodeType() {
        return columnNodeType;
    }

    public void setColumnNodeType(String columnNodeType) {
        this.columnNodeType = columnNodeType;
    }
}
