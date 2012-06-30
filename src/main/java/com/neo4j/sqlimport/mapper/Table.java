package com.neo4j.sqlimport.mapper;

import java.util.List;

/**
 * An RDBMS table
 * User: luanne
 * Date: 30/06/12
 * Time: 7:27 PM
 */
public class Table {

    private String tableName;
    private List<Column> columns;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }
}
