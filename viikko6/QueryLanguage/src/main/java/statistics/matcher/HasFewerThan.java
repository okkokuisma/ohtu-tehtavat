/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistics.matcher;

import statistics.Player;

/**
 *
 * @author okkokuisma
 */
public class HasFewerThan implements Matcher {
    private HasAtLeast matcher;

    public HasFewerThan(int value, String category) {
        matcher = new HasAtLeast(value, category);
    }
    
    public boolean matches(Player p) {
        return !matcher.matches(p);
    }
}
