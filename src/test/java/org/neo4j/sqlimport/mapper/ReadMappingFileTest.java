package org.neo4j.sqlimport.mapper;

import com.neo4j.sqlimport.mapper.*;
import junit.framework.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * Test to read the mapping file
 * User: luanne
 * Date: 30/06/12
 * Time: 9:54 PM
 */
public class ReadMappingFileTest {

    @Test
    public void testNodeTypesAndIndex() {
        Mapper mapper = new Mapper();
        mapper.init("mappingExample.xml");
        Map<String,MappedNode> mappedNodes = mapper.getMappedNodes();
        Assert.assertNotNull(mappedNodes);
        Assert.assertEquals(4, mappedNodes.size());
        Assert.assertEquals("customer", mappedNodes.get("customer").getNodeType());
        Assert.assertEquals("customerIndex", mappedNodes.get("customer").getIndexName());

        Assert.assertEquals("location", mappedNodes.get("location").getNodeType());
        Assert.assertEquals("locationIndex", mappedNodes.get("location").getIndexName());

        Assert.assertEquals("offer", mappedNodes.get("offer").getNodeType());
        Assert.assertEquals("offerIndex", mappedNodes.get("offer").getIndexName());

        Assert.assertEquals("merchant",mappedNodes.get("merchant").getNodeType());
        Assert.assertEquals("merchantIndex", mappedNodes.get("merchant").getIndexName());

    }

    @Test
    public void testNodeTables() {
        Mapper mapper = new Mapper();
        mapper.init("mappingExample.xml");
        Map<String,MappedNode> mappedNodes = mapper.getMappedNodes();
        Assert.assertNotNull(mappedNodes);
        Assert.assertEquals(4,mappedNodes.size());

        //Customer
        Table table = mappedNodes.get("customer").getTable();
        Assert.assertNotNull(table);
        Assert.assertEquals("customer",table.getTableName());
        List<Column> columns = table.getColumns();
        Assert.assertNotNull(columns);
        Assert.assertEquals(3,columns.size());
        PropertyColumn idColumn=null;
        PropertyColumn nameColumn=null;
        RelationshipColumn locationIdColumn=null;
        for(Column col : columns) {
            if(col.getColumnName().equals("id")) {
               idColumn = (PropertyColumn)col;
            }
            if(col.getColumnName().equals("name")) {
                nameColumn=(PropertyColumn) col;
            }
            if(col.getColumnName().equals("locationId")) {
                locationIdColumn=(RelationshipColumn) col;
            }
        }
        Assert.assertEquals("id",idColumn.getPropertyName());
        Assert.assertEquals(true,idColumn.isIndexed());
        Assert.assertEquals("name",nameColumn.getPropertyName());
        Assert.assertEquals("locatedIn",locationIdColumn.getRelationshipType());
        Assert.assertEquals("location",locationIdColumn.getColumnNodeType());
        Assert.assertEquals(true,locationIdColumn.isStartNode());


        //location
        table = mappedNodes.get("location").getTable();
        Assert.assertNotNull(table);
        Assert.assertEquals("location",table.getTableName());
        columns = table.getColumns();
        Assert.assertNotNull(columns);
        Assert.assertEquals(2,columns.size());

        PropertyColumn propLocationIdColumn=null;
         nameColumn=null;
        for(Column col : columns) {
            if(col.getColumnName().equals("locationId")) {
                propLocationIdColumn = (PropertyColumn)col;
            }
            if(col.getColumnName().equals("name")) {
                nameColumn=(PropertyColumn) col;
            }

        }
        Assert.assertEquals("id",propLocationIdColumn.getPropertyName());
        Assert.assertEquals(true,propLocationIdColumn.isIndexed());
        Assert.assertEquals("name",nameColumn.getPropertyName());



        //offer
        table = mappedNodes.get("offer").getTable();
        Assert.assertNotNull(table);
        Assert.assertEquals("offer",table.getTableName());
        columns = table.getColumns();
        Assert.assertNotNull(columns);
        Assert.assertEquals(5,columns.size());

        PropertyColumn startDateColumn=null, endDateColumn=null;
        idColumn=null;
        nameColumn=null;
        RelationshipColumn merchantIdRelColumn=null;
        for(Column col : columns) {
            if(col.getColumnName().equals("id")) {
                idColumn=(PropertyColumn) col;
            }
            if(col.getColumnName().equals("name")) {
                nameColumn = (PropertyColumn) col;
            }
            if(col.getColumnName().equals("startdate")) {
                startDateColumn = (PropertyColumn) col;
            }
            if(col.getColumnName().equals("enddate")) {
                endDateColumn = (PropertyColumn) col;
            }
            if(col.getColumnName().equals("merchantId")) {
                merchantIdRelColumn = (RelationshipColumn)col;
            }
        }
        Assert.assertEquals("id",idColumn.getPropertyName());
        Assert.assertEquals(true,idColumn.isIndexed());
        Assert.assertEquals("name",nameColumn.getPropertyName());
        Assert.assertEquals("startdate",startDateColumn.getPropertyName());
        Assert.assertEquals("enddate",endDateColumn.getPropertyName());
        Assert.assertEquals("hasOffer",merchantIdRelColumn.getRelationshipType());
        Assert.assertEquals("merchant",merchantIdRelColumn.getColumnNodeType());
        Assert.assertEquals(false,merchantIdRelColumn.isStartNode());

        //merchant
        table = mappedNodes.get("merchant").getTable();
        Assert.assertNotNull(table);
        Assert.assertEquals("merchant",table.getTableName());
        columns = table.getColumns();
        Assert.assertNotNull(columns);
        Assert.assertEquals(2,columns.size());
        idColumn=null;
        nameColumn=null;
        for(Column col : columns) {
            if(col.getColumnName().equals("id")) {
                idColumn=(PropertyColumn) col;
            }
            if(col.getColumnName().equals("name")) {
                nameColumn = (PropertyColumn) col;
            }
        }
        Assert.assertEquals("id",idColumn.getPropertyName());
        Assert.assertEquals(true,idColumn.isIndexed());
        Assert.assertEquals("name",nameColumn.getPropertyName());

    }

}
