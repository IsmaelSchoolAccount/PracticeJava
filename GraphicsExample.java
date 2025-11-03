import javax.swing.JFrame;

public class GraphicsExample {

    public JFrame frame;

    public GraphicsExample(Player player) {
        frame = new JFrame("My Graphics Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        DrawingPanel panel = new DrawingPanel(player);
        frame.add(panel); // Add the panel to the frame
        frame.setVisible(true);
    }
}