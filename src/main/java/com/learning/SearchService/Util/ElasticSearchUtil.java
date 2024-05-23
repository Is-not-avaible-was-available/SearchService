package com.learning.SearchService.Util;

import co.elastic.clients.elasticsearch._types.query_dsl.*;


import java.util.ArrayList;
import java.util.List;
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

    public static Supplier<Query> supplierBoolQuery(String title, Integer qty){
        return () -> Query.of(q->q.bool(boolQuery(title, qty)));
    }


    public static BoolQuery boolQuery(String title, Integer qty){
        var boolQuery = new BoolQuery.Builder();
        return boolQuery.filter(termQuery(title)).must(matchQueries(qty)).build();
    }

    public static List<Query> termQuery(String fieldValue){
        List<Query> terms = new ArrayList<>();
        var termQuery = new TermQuery.Builder();
        terms.add(Query
                .of(q->q.term(termQuery.field("title").value(fieldValue).build())));

        return terms;
    }

    public static List<Query> matchQueries(Integer quantity){
        List<Query> matches = new ArrayList<>();
        var matchQuery = new MatchQuery.Builder();
        matches.add(Query
                .of(q->q.match(matchQuery.field("qty").query(quantity).build())));
        return matches;
    }

}
