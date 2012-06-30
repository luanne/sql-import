package com.neo4j.sqlimport.mapper;

import java.util.List;

/**
 * An association table that maps to relationships
 * User: luanne
 * Date: 30/06/12
 * Time: 9:16 PM
 */
public class RelationshipTable {

    private String tableName;
    RelationshipColumn start;
    RelationshipColumn end;
    List<PropertyColumn> properties;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public RelationshipColumn getStart() {
        return start;
    }

    public void setStart(RelationshipColumn start) {
        this.start = start;
    }

    public RelationshipColumn getEnd() {
        return end;
    }

    public void setEnd(RelationshipColumn end) {
        this.end = end;
    }

    public List<PropertyColumn> getProperties() {
        return properties;
    }

    public void setProperties(List<PropertyColumn> properties) {
        this.properties = properties;
    }
}
