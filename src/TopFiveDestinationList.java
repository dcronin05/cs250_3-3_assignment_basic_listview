import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class TopFiveDestinationList {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TopDestinationListFrame topDestinationListFrame = new TopDestinationListFrame();
                topDestinationListFrame.setTitle("Top 5 Destination List");
                topDestinationListFrame.setVisible(true);
            }
        });
    }
}


class TopDestinationListFrame extends JFrame {
    private DefaultListModel listModel;

    public TopDestinationListFrame() {
        super("Top Five Destination List");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(900, 750);

        listModel = new DefaultListModel();


        //Make updates to your top 5 list below. Import the new image files to resources directory.
        addDestinationNameAndPicture("1. Top Destination (short sentence description)",
                new ImageIcon(getClass().getResource("/resources/TestImage.jpg")),
                "https://www.google.com"
        );
        addDestinationNameAndPicture("2. 2nd Top Destination",
                new ImageIcon(getClass().getResource("/resources/TestImage.jpg")),
                "https://www.google.com"
        );
        addDestinationNameAndPicture("3. 3rd Top Destination",
                new ImageIcon(getClass().getResource("/resources/TestImage.jpg")),
                "https://www.google.com"
        );
        addDestinationNameAndPicture("4. 4th Top Destination",
                new ImageIcon(getClass().getResource("/resources/TestImage.jpg")),
                "https://www.google.com"
        );
        addDestinationNameAndPicture("5. 5th Top Destination",
                new ImageIcon(getClass().getResource("/resources/TestImage.jpg")),
                "https://www.google.com"
        );

        JList list = new JList(listModel);
        JScrollPane scrollPane = new JScrollPane(list);

        TextAndIconListCellRenderer renderer = new TextAndIconListCellRenderer(10);

        list.setCellRenderer(renderer);

        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    private void addDestinationNameAndPicture(String text, Icon icon, String url) {
        TextAndIcon tai = new TextAndIcon(text, icon, url);
        listModel.addElement(tai);
    }
}


class TextAndIcon {
    private String text;
    private Icon icon;
    private String url;

    public TextAndIcon(String text, Icon icon, String url) {
        this.text = text;
        this.icon = icon;
        this.url = url;
    }

    public String getText() {
        return text;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}


class TextAndIconListCellRenderer extends JLabel implements ListCellRenderer {
    private static final Border NO_FOCUS_BORDER = new EmptyBorder(1, 1, 1, 1);

    private Border insideBorder;

    public TextAndIconListCellRenderer() {
        this(0, 0, 0, 0);
    }

    public TextAndIconListCellRenderer(int padding) {
        this(padding, padding, padding, padding);
    }

    public TextAndIconListCellRenderer(int topPadding, int rightPadding, int bottomPadding, int leftPadding) {
        insideBorder = BorderFactory.createEmptyBorder(topPadding, leftPadding, bottomPadding, rightPadding);
        setOpaque(true);
    }

    public Component getListCellRendererComponent(JList list, Object value,
                                                  int index, boolean isSelected, boolean hasFocus) {
        // The object from the combo box model MUST be a TextAndIcon.
        TextAndIcon tai = (TextAndIcon) value;

        // Sets text and icon on 'this' JLabel.
        setText(tai.getText());
        setIcon(tai.getIcon());

        // Sets different foreground and background colors for selected cells
        list.setSelectionForeground(Color.white);
        list.setSelectionBackground(Color.darkGray);

        // Sets different foreground and background colors for unselected cells
        list.setBackground(Color.black);
        list.setForeground(Color.gray);

        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        Border outsideBorder;

        if (hasFocus) {
            outsideBorder = UIManager.getBorder("List.focusCellHighlightBorder");
        } else {
            outsideBorder = UIManager.getBorder("");
        }

        setBorder(BorderFactory.createCompoundBorder(outsideBorder, insideBorder));
        setComponentOrientation(list.getComponentOrientation());
        setEnabled(list.isEnabled());
        setFont(list.getFont());

        return this;
    }

    // The following methods are overridden to be empty for performance
    // reasons. If you want to understand better why, please read:
    //
    // http://java.sun.com/javase/6/docs/api/javax/swing/DefaultListCellRenderer.html#override

    public void validate() {
    }

    public void invalidate() {
    }

    public void repaint() {
    }

    public void revalidate() {
    }

    public void repaint(long tm, int x, int y, int width, int height) {
    }

    public void repaint(Rectangle r) {
    }
}