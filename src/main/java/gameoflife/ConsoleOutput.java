package gameoflife;

import java.util.function.Consumer;

public class ConsoleOutput implements Consumer<boolean[][]> {

    public ConsoleOutput() {
    	ANSI.hideCursor();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
        	ANSI.showCursor();
            ANSI.clear();
            System.out.flush();
        }));
    }

    @Override
    public void accept(boolean[][] cells) {
    	ANSI.bold();
        for (int r = 0; r < cells.length; r++) {
            for (int c = 0; c < cells[r].length; c++) {
                System.out.print(cells[r][c] ? "██" : "  ");
            }
            System.out.println();
        }
        ANSI.reset();
        ANSI.moveTerminalCursorUp(cells.length);
        System.out.flush();
    }
    
    private static class ANSI {
        private static void bold() {
            System.out.print("\033[1m");
        }

        private static void reset() {
            System.out.print("\033[00m");
        }

        private static void clear() {
            System.out.print("\033c");
        }

        private static void hideCursor() {
            System.out.print("\033[?25l");
        }

        private static void showCursor() {
            System.out.print("\033[?25h");
        }

        private static void moveTerminalCursorUp(int nLines) {
            System.out.print("\033[" + nLines + "A");
        }
    }
}
