package com.learning.SearchService.Util;

import co.elastic.clients.elasticsearch._types.query_dsl.FuzzyQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchAllQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;

import java.util.function.Supplier;

public class ElasticSearchUtil {

    public static MatchAllQuery matchAllQuery(){
        return new MatchAllQuery.Builder().build();
    }

    public static Supplier<Query> matchAllQuerySupplier(){
        return () -> Query.of(q ->q.matchAll(matchAllQuery()));
    }

    public static Supplier<Query> matchAllProductQuerySupplier(){
        return ()-> Query.of(q -> q.matchAll(matchAllQuery()));
    }

    public static MatchQuery matchQuery(String filedName){
        return new MatchQuery.Builder().field("title").query(filedName).build();
    }

    public static Supplier<Query> matchQuerySupplier(String fieldName){
        return () -> Query.of(q -> q.match(matchQuery(fieldName)));
    }

    public static FuzzyQuery fuzzyQuery(String approxValue){
        return new FuzzyQuery.Builder().field("title").value(approxValue).build();
    }

    public static Supplier<Query> fuzzyQuerySupplier(String approxValue){
        return () -> Query.of(q -> q.fuzzy(fuzzyQuery(approxValue)));
    }
}
