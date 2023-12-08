import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {
    private JButton dersKayitButton;
    private JButton ogrenciKayitButton;
    private JPanel JPanel;

    public Menu()
    {


        setTitle("Ders Kayıt Uygulaması");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(350,  150);
        setLocationRelativeTo(null);
        setVisible(true);
        setContentPane(JPanel);


        dersKayitButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                DersKayit dersKayit = new DersKayit();
                dersKayit.setVisible(true);
            }
        });


        ogrenciKayitButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                OgrenciKayit ogrenciKayit = new OgrenciKayit();
                ogrenciKayit.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        new Menu();
    }
}
