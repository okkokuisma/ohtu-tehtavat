
package ohtu;

public class Player implements Comparable<Player> {
    private String name;
    private String nationality;
    private int goals;
    private int assists;
    private String team;

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    
    public String getNationality() {
        return nationality;
    }

    public String getTeam() {
        return team;
    }

    public int getPoints() {
        return goals + assists;
    }

    public int getGoals() {
        return goals;
    }

    public int getAssists() {
        return assists;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("%-20s",name) + team + "\t" + goals + "+" + assists + "=" + getPoints();
    }
    
    public int compareTo(Player t) {
        return t.getPoints()-this.getPoints();
    }
}
