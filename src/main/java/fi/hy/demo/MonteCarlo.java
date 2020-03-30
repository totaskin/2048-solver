package fi.hy.demo;

public class MonteCarlo implements Bot {

    private Integer runs;

    public MonteCarlo(Integer runs) {
        this.runs = runs;
    }

    @Override
    public direction decideMove(Board board) {

        long moveUp = 0;
        long moveUpCount = 1;

        long moveDown = 0;
        long moveDownCount = 1;

        long moveRight = 0;
        long moveRightCount = 1;

        long moveLeft = 0;
        long moveLeftCount = 1;

        int input;
        for (int i = 0; i < runs; i++) {

            Board boardCopy = board.copyBoard();
            int count = 0;
            int firstMove = -1;
            int gameMoves = 0;

            while (!boardCopy.getGameState().equals(Board.State.over)) {
                if (gameMoves % 100 == 0) {
                    System.out.println("100s");
                }
                gameMoves++;
                //generate random next move
                input = getRandom();

                makeMove(input, boardCopy);

                if (count == 0) {
                    firstMove = input;
                    if (input == 0) {
                        moveUpCount++;
                    } else if (input == 1) {
                        moveDownCount++;
                    } else if (input == 2) {
                        moveRightCount++;
                    } else if (input == 3) {
                        moveLeftCount++;
                    }
                }
                count++;
            }

            // sum random game scores
            if (firstMove == 0) {
                moveUp += boardCopy.getScore();
            } else if (firstMove == 1) {
                moveDown += boardCopy.getScore();
            } else if (firstMove == 2) {
                moveRight += boardCopy.getScore();
            } else if (firstMove == 3) {
                moveLeft += boardCopy.getScore();
            }
        }

        // random game averages
        moveUp = moveUp / moveUpCount;
        moveDown = moveDown / moveDownCount;
        moveRight = moveRight / moveRightCount;
        moveLeft = moveLeft / moveLeftCount;

        // return best move
        direction x = getDirection(moveUp, moveDown, moveRight, moveLeft);
        if (x != null) return x;


        System.out.println("should not come herer");
        return direction.DOWN;
    }

    private int getRandom() {
        return (int) (Math.random() * 4);
    }

    public direction getDirection(long moveUp, long moveDown, long moveRight, long moveLeft) {
        if (moveUp >= moveDown && moveUp >= moveLeft && moveUp >= moveRight) {
            return direction.UP;
        } else if (moveRight >= moveDown && moveRight >= moveLeft && moveRight >= moveUp) {
            return direction.RIGHT;
        } else if (moveLeft >= moveDown && moveLeft >= moveUp && moveLeft >= moveRight) {
            return direction.LEFT;
        } else if (moveDown >= moveUp && moveDown >= moveRight && moveDown >= moveLeft) {
            return direction.DOWN;
        }
        return null;
    }

    public void makeMove(int input, Board boardCopy) {
        switch (input) {
            case 0:
                boardCopy.moveUp();
                break;
            case 1:
                boardCopy.moveDown();
                break;
            case 2:
                boardCopy.moveRight();
                break;
            case 3:
                boardCopy.moveLeft();
                break;

        }
    }
}
