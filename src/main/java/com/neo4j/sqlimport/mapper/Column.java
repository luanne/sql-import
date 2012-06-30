package com.neo4j.sqlimport.mapper;

/**
 * A column of an RDBMS table
 * User: luanne
 * Date: 30/06/12
 * Time: 7:29 PM
 */
public class Column {

    private String columnName;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }
}
