/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistics.matcher;

import java.util.ArrayList;
import statistics.Player;

/**
 *
 * @author okkokuisma
 */
public class QueryBuilder {
    
    private ArrayList<Matcher> matchers;

    public QueryBuilder() {
        matchers = new ArrayList<>();
        matchers.add(new All());
    }
    
    public QueryBuilder playsIn(String team) {
        matchers.add(new PlaysIn(team));
        return this;
    }
    
    public QueryBuilder hasAtLeast(int value, String category) {
        matchers.add(new HasAtLeast(value, category));
        return this;
    }
    
    public QueryBuilder hasFewerThan(int value, String category) {
        matchers.add(new HasFewerThan(value, category));
        return this;
    }
    
    public QueryBuilder oneOf(Matcher... matchers) {
        this.matchers.add(new Or(matchers));
        return this;
    }

    public Matcher build() {
        Matcher[] matcherList = new Matcher[matchers.size()];
        Matcher matcher = new And(matchers.toArray(matcherList));
        matchers = new ArrayList<>();
        return matcher;
    }
}
