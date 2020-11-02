/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author okkokuisma
 */
public class StatisticsTest {
 
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;

    @Before
    public void setUp(){
        stats = new Statistics(readerStub);
    }  
    
    @Test
    public void searchReturnsRightPlayer() {
        assertEquals("Kurri", stats.search("Kur").getName());
    }
    
    @Test
    public void searchReturnsNullWHenNoPlayerFound() {
        assertEquals(null, stats.search("Manninen"));
    }
    
    @Test
    public void teamReturnsRightPlayers() {
        List<Player> players = stats.team("EDM");
        assertEquals(3, players.size());
        for (Player player : players) {
            assertTrue(player.getTeam().equals("EDM"));
        }
    }
    
    @Test
    public void topScorersAreInTheRightOrder() {
        List<Player> players = stats.topScorers(4);
        assertEquals(5, players.size());
        for (int i = 1; i < players.size(); i++) {
            if (players.get(i-1).compareTo(players.get(i)) > 0) {
                fail("Top scorers are in a wrong order");
            }
        }
    }
}