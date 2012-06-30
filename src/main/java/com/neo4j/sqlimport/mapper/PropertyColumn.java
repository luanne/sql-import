package com.neo4j.sqlimport.mapper;

/**
 * An RDBMS column that maps to a Neo4j property
 * User: luanne
 * Date: 30/06/12
 * Time: 7:30 PM
 */
public class PropertyColumn extends Column{

    private String propertyName;
    private boolean indexed;

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public boolean isIndexed() {
        return indexed;
    }

    public void setIndexed(boolean indexed) {
        this.indexed = indexed;
    }
}
