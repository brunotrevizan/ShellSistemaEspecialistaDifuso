package shell.frame;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import shell.entities.Conectivo;
import shell.entities.Regra;
import shell.entities.Valor;
import shell.entities.Variavel;

public class DialogRegras extends Dialog {

	private static String quebraLinha = System.getProperty("line.separator");
	
	protected Object result;
	protected Shell shlInserirRegras;
	
	private List<Variavel> variaveis;
	private Combo comboVariavelUm;
	private Combo comboVariavelDois;
	private Combo comboVariavelTres;
	private Combo comboValorVariavelUm;
	private Combo comboValorVariavelDois;
	private Combo comboValorVariavelTres;
	private Combo comboConectivo;
	private Button btnInserir;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public DialogRegras(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlInserirRegras.open();
		shlInserirRegras.layout();
		Display display = getParent().getDisplay();
		while (!shlInserirRegras.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shlInserirRegras = new Shell(getParent(), getStyle());
		shlInserirRegras.setSize(330, 180);
		shlInserirRegras.setText("Inserir Regras");
		
		Button btnX = new Button(shlInserirRegras, SWT.NONE);
		btnX.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlInserirRegras.close();
			}
		});
		btnX.setBounds(304, 1, 19, 18);
		btnX.setText("X");
		
		Label lblSe = new Label(shlInserirRegras, SWT.NONE);
		lblSe.setBounds(5, 23, 19, 15);
		lblSe.setText("Se");
		
		comboVariavelUm = new Combo(shlInserirRegras, SWT.READ_ONLY);
		comboVariavelUm.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String nomeVariavel = comboVariavelUm.getText();
				comboValorVariavelUm.removeAll();
				for (Variavel variavel : variaveis) {
					if(variavel.getNome().equals(nomeVariavel)){
						for (Valor valor : variavel.getValores()) {
							comboValorVariavelUm.add(valor.getNome());
						}
					}
				}
			}
		});
		comboVariavelUm.setBounds(47, 20, 120, 23);
		
		Label labelIgualUm = new Label(shlInserirRegras, SWT.NONE);
		labelIgualUm.setBounds(178, 23, 19, 15);
		labelIgualUm.setText("=");
		
		comboValorVariavelUm = new Combo(shlInserirRegras, SWT.READ_ONLY);
		comboValorVariavelUm.setBounds(200, 20, 120, 23);
		
		comboConectivo = new Combo(shlInserirRegras, SWT.READ_ONLY);
		comboConectivo.setBounds(5, 49, 36, 23);
		
		comboVariavelDois = new Combo(shlInserirRegras, SWT.READ_ONLY);
		comboVariavelDois.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String nomeVariavel = comboVariavelDois.getText();
				comboValorVariavelDois.removeAll();
				for (Variavel variavel : variaveis) {
					if(variavel.getNome().equals(nomeVariavel)){
						for (Valor valor : variavel.getValores()) {
							comboValorVariavelDois.add(valor.getNome());
						}
					}
				}
			}
		});
		comboVariavelDois.setBounds(47, 49, 120, 23);
		
		Label labelIgualDois = new Label(shlInserirRegras, SWT.NONE);
		labelIgualDois.setText("=");
		labelIgualDois.setBounds(178, 52, 19, 15);
		
		comboValorVariavelDois = new Combo(shlInserirRegras, SWT.READ_ONLY);
		comboValorVariavelDois.setBounds(200, 49, 120, 23);
		
		Label lblEnto = new Label(shlInserirRegras, SWT.NONE);
		lblEnto.setText("Ent\u00E3o");
		lblEnto.setBounds(5, 86, 35, 15);
		
		comboVariavelTres = new Combo(shlInserirRegras, SWT.READ_ONLY);
		comboVariavelTres.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String nomeVariavel = comboVariavelTres.getText();
				comboValorVariavelTres.removeAll();
				for (Variavel variavel : variaveis) {
					if(variavel.getNome().equals(nomeVariavel)){
						for (Valor valor : variavel.getValores()) {
							comboValorVariavelTres.add(valor.getNome());
						}
					}
				}
			}
		});
		comboVariavelTres.setBounds(47, 78, 120, 23);
		
		Label labelIgualTres = new Label(shlInserirRegras, SWT.NONE);
		labelIgualTres.setText("=");
		labelIgualTres.setBounds(178, 81, 19, 15);
		
		comboValorVariavelTres = new Combo(shlInserirRegras, SWT.READ_ONLY);
		comboValorVariavelTres.setBounds(200, 78, 120, 23);
		
		btnInserir = new Button(shlInserirRegras, SWT.NONE);
		btnInserir.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String nomeVariavelUm = comboVariavelUm.getText();
				String nomeVariavelDois = comboVariavelDois.getText();
				String nomeVariavelTres = comboVariavelTres.getText();
				
				String valorVariavelUm = comboValorVariavelUm.getText();
				String valorVariavelDois = comboValorVariavelDois.getText();
				String valorVariavelTres = comboValorVariavelTres.getText();
				
				String mensagem = "";
				
				mensagem = validaDadosCombos(nomeVariavelUm, nomeVariavelDois, nomeVariavelTres, valorVariavelUm, valorVariavelDois, valorVariavelTres, mensagem);
				
				if(!mensagem.equals("")){
					MessageBox dialog = new MessageBox(shlInserirRegras, SWT.ICON_ERROR
							| SWT.OK );
					dialog.setText("Insira os dados");
					dialog.setMessage(mensagem);
					dialog.open();
				} else {
					Regra regra = new Regra();
					regra.setConectivo(Conectivo.retornaConectivo(comboConectivo.getText()));
					regra.setVariavelUm(buscaVariavelPorNome(nomeVariavelUm));
					regra.setVariavelDois(buscaVariavelPorNome(nomeVariavelDois));
					regra.setVariavelTres(buscaVariavelPorNome(nomeVariavelTres));
					regra.setValorVariavelUm(buscaValorPorNome(regra.getVariavelUm(),valorVariavelUm));
					regra.setValorVariavelDois(buscaValorPorNome(regra.getVariavelDois(),valorVariavelDois));
					regra.setValorVariavelTres(buscaValorPorNome(regra.getVariavelTres(),valorVariavelTres));
					
					regra.setIdUnico((TelaPrincipal.sistemaEspecialistaDifuso.getRegras().size()));
					TelaPrincipal.sistemaEspecialistaDifuso.getRegras().add(regra);
					shlInserirRegras.close();
				}
			}

			private Valor buscaValorPorNome(Variavel variavelUm, String valorVariavelUm) {
				for (Valor valor : variavelUm.getValores()) {
					if(valor.getNome().equals(valorVariavelUm)){
						return valor;
					}
				}
				return null;
			}

			private Variavel buscaVariavelPorNome(String nomeVariavelUm) {
				for (Variavel variavel : variaveis) {
					if(variavel.getNome().equals(nomeVariavelUm)){
						return variavel;
					}
				}
				return null;
			}

			private String validaDadosCombos(String nomeVariavelUm,
					String nomeVariavelDois, String nomeVariavelTres,
					String valorVariavelUm, String valorVariavelDois,
					String valorVariavelTres, String mensagem) {
				if(nomeVariavelUm == null || nomeVariavelUm.equals("")){
					mensagem += "Nome da variável UM é obrigatório" + quebraLinha;
				}
				
				if(nomeVariavelDois == null || nomeVariavelDois.equals("")){
					mensagem += "Nome da variável DOIS é obrigatório" + quebraLinha;
				}
				
				if(nomeVariavelTres == null || nomeVariavelTres.equals("")){
					mensagem += "Nome da variável TRÊS é obrigatório" + quebraLinha;
				}
				
				if(valorVariavelUm == null || valorVariavelUm.equals("")){
					mensagem += "Valor da variável UM é obrigatório" + quebraLinha;
				}
				
				if(valorVariavelDois == null || valorVariavelDois.equals("")){
					mensagem += "Valor da variável DOIS é obrigatório" + quebraLinha;
				}
				
				if(valorVariavelTres == null || valorVariavelTres.equals("")){
					mensagem += "Valor da variável TRÊS é obrigatório" + quebraLinha;
				}

				return mensagem;
			}
		});
		btnInserir.setBounds(147, 119, 75, 25);
		btnInserir.setText("Inserir");
		shlInserirRegras.setLocation(getParent().getLocation());
		popularCombos();
	}

	private void popularCombos() {
		for (Variavel variavel : variaveis) {
			comboVariavelUm.add(variavel.getNome());
			comboVariavelDois.add(variavel.getNome());
			comboVariavelTres.add(variavel.getNome());
		}
		comboConectivo.add(Conectivo.E.name());
		comboConectivo.add(Conectivo.OU.name());
	}

	public List<Variavel> getVariaveis() {
		return variaveis;
	}

	public void setVariaveis(List<Variavel> variaveis) {
		this.variaveis = variaveis;
	}
}
