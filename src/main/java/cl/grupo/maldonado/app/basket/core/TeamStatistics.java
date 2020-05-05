package cl.grupo.maldonado.app.basket.core;

public class TeamStatistics {

    private Integer pointPerGameLocal;
    private Integer pointPerGameVisitor;

    private Integer assistsPerGame;
    private Integer reboundsPerGame;
    private Integer threePointersPerGame;

    private Integer countGames;
    private Integer countGamesVisitor;
    private Integer countGamesLocal;

    public Integer getPointPerGameLocal() {
        return pointPerGameLocal;
    }

    public void setPointPerGameLocal(Integer pointPerGameLocal) {
        this.pointPerGameLocal = pointPerGameLocal;
    }

    public Integer getPointPerGameVisitor() {
        return pointPerGameVisitor;
    }

    public void setPointPerGameVisitor(Integer pointPerGameVisitor) {
        this.pointPerGameVisitor = pointPerGameVisitor;
    }

    public Integer getAssistsPerGame() {
        return assistsPerGame;
    }

    public void setAssistsPerGame(Integer assistsPerGame) {
        this.assistsPerGame = assistsPerGame;
    }

    public Integer getReboundsPerGame() {
        return reboundsPerGame;
    }

    public void setReboundsPerGame(Integer reboundsPerGame) {
        this.reboundsPerGame = reboundsPerGame;
    }

    public Integer getThreePointersPerGame() {
        return threePointersPerGame;
    }

    public void setThreePointersPerGame(Integer threePointersPerGame) {
        this.threePointersPerGame = threePointersPerGame;
    }

    public Integer getCountGames() {
        return countGames;
    }

    public void setCountGames(Integer countGames) {
        this.countGames = countGames;
    }

    public Integer getCountGamesVisitor() {
        return countGamesVisitor;
    }

    public void setCountGamesVisitor(Integer countGamesVisitor) {
        this.countGamesVisitor = countGamesVisitor;
    }

    public Integer getCountGamesLocal() {
        return countGamesLocal;
    }

    public void setCountGamesLocal(Integer countGamesLocal) {
        this.countGamesLocal = countGamesLocal;
    }
}
