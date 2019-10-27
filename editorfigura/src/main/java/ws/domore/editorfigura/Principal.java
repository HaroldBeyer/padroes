package ws.domore.editorfigura;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.HashSet;
import java.util.Set;
import java.awt.geom.Line2D;
import java.util.HashSet;
import java.util.Set;


import javax.swing.DebugGraphics;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 *
 * @author mariojp
 */
public class Principal extends JFrame implements ActionListener,MouseListener {

    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JButton botaoQuadrado = new JButton("QUADRADO");
	private JButton botaoCirculo = new JButton("CIRCULO");
	private JButton botaoApagar = new JButton("APAGAR");
	private JButton botaoTriangulo = new JButton("TRIANGULO");
  private JButton botaoLimparUltimoDesenho = new JButton("LIMPAR ULTIMO OBJETO");

	private String selecionado = "";

	private Set<Shape> figuras = new HashSet<Shape>();
	
	//Criando um JPanel com layoutManager null
    private JPanel painel = new MeuPanel(null, figuras);

    
    public Principal() {
        //ITTULO DA JANELA
        setTitle("FIGURAS");
        
        //Interface
        JPanel botoes = new JPanel();
        botoes.setLayout(new GridLayout(5, 1));
        botoes.add(botaoQuadrado);
        botoes.add(botaoCirculo);
      	botoes.add(botaoTriangulo);
    		botoes.add(botaoLimparUltimoDesenho);
        botoes.add(botaoApagar);

        JPanel lateral = new JPanel();
        lateral.add(botoes);      		
      		
        //Painel lateral
        this.add(BorderLayout.WEST, lateral);
        //centro
        
        painel.setBackground(Color.WHITE);
        this.add(BorderLayout.CENTER, painel);

        
        //ACOES
        botaoCirculo.addActionListener(this);
        botaoQuadrado.addActionListener(this);
        botaoTriangulo.addActionListener(this);
        botaoApagar.addActionListener(this);
        botaoLimparUltimoDesenho.addActionListener(this);


        painel.addMouseListener(this);

        this.setSize(Toolkit.getDefaultToolkit().getScreenSize().width/2, Toolkit.getDefaultToolkit().getScreenSize().height/2);
        
        setLocationRelativeTo(null);

        this.setVisible(true);
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    }

	public void actionPerformed(ActionEvent e) {
		int ultimo = 0;
		int i = 0;
		JButton botao = (JButton) e.getSource();
		if (botao.getText().contains("QUADRADO")) {
			selecionado = "QUADRADO";
		}
		if (botao.getText().contains("CIRCULO")) {
			selecionado = "CIRCULO";
		}
    if(botao.getText().contains("TRIANGULO")) {
			selecionado = "TRIANGULO";
		}
     if(botao.getText().contains("APAGAR")) {
        figuras.clear();
    		selecionado = "";
     }
		if (botao.getText().contains("LIMPAR ULTIMO OBJETO")) {
  		selecionado = "";

			if (!figuras.isEmpty()) {
				ultimo = 0;
				for (Shape shape : figuras) {
					ultimo++;
				}
				for (Shape shape : figuras) {
					i++;
					if (ultimo == i) {
						figuras.remove(shape);
					}
				}
			}
			this.painel.updateUI();
		}
	}

    public static void main(String[] args) {
        Principal j = new Principal();
        j.setVisible(true);

    }

	@Override
	public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if(selecionado.contentEquals("CIRCULO")) {
        	figuras.add(new Ellipse2D.Double(x, y, 10, 10));
        }else if(selecionado.contentEquals("QUADRADO")) {
        	figuras.add(new Rectangle2D.Double(x, y, 10, 10));
        }else if(selecionado.contentEquals("TRIANGULO")) {
			figuras.add(new Line2D.Double(x - 10, y, x, y));
			figuras.add(new Line2D.Double(x - 10, y - 10, x, y));
			figuras.add(new Line2D.Double(x - 10, y - 10, x - 10, y));
		}
        this.painel.updateUI();		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	
}