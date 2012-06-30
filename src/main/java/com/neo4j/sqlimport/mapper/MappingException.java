package com.neo4j.sqlimport.mapper;

/**
 * Mapping Exception
 * User: luanne
 * Date: 30/06/12
 * Time: 11:28 PM
 */
public class MappingException extends RuntimeException{

    public MappingException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
