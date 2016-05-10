package shell.frame;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import shell.entities.Valor;
import shell.entities.Variavel;

public class ValoresVariaveis extends Dialog {
	private static String quebraLinha = System.getProperty("line.separator");
	protected Object result;
	protected Shell shlValoresDasVariveis;
	private Text inputNome;
	private Text inputSuporteInicio;
	private Text inputNucleoInicio;
	private Text inputSuporteFim;
	private Text inputNucleoFim;
	private Button btnExcluir;
	private Button btnAdicionar;
	
	private Variavel variavel;
	private Button btnX;
	private List listaValores;
	
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public ValoresVariaveis(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlValoresDasVariveis.open();
		shlValoresDasVariveis.layout();
		Display display = getParent().getDisplay();
		while (!shlValoresDasVariveis.isDisposed()) {
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
		shlValoresDasVariveis = new Shell(getParent(), getStyle());
		shlValoresDasVariveis.setTouchEnabled(true);
		shlValoresDasVariveis.setSize(450, 300);
		shlValoresDasVariveis.setText("Valores das Vari\u00E1veis");
		
		Label lblNome = new Label(shlValoresDasVariveis, SWT.NONE);
		lblNome.setBounds(22, 10, 55, 15);
		lblNome.setText("Nome");
		
		Label lblSuporte = new Label(shlValoresDasVariveis, SWT.NONE);
		lblSuporte.setText("Suporte");
		lblSuporte.setBounds(22, 44, 55, 15);
		
		Label lblNcleo = new Label(shlValoresDasVariveis, SWT.NONE);
		lblNcleo.setText("N\u00FAcleo");
		lblNcleo.setBounds(22, 79, 55, 15);
		
		inputNome = new Text(shlValoresDasVariveis, SWT.BORDER);
		inputNome.setBounds(83, 10, 76, 21);
		
		inputSuporteInicio = new Text(shlValoresDasVariveis, SWT.BORDER);
		inputSuporteInicio.addListener(SWT.Verify, new Listener() {
			public void handleEvent(Event e) {
				String string = e.text;
				char[] chars = new char[string.length()];
				string.getChars(0, chars.length, chars, 0);
				for (int i = 0; i < chars.length; i++) {
					if (!('0' <= chars[i] && chars[i] <= '9') && (chars[i] != '-')) {
						e.doit = false;
						return;
					}
				}
			}
		});
		inputSuporteInicio.setBounds(83, 44, 76, 21);
		
		inputSuporteFim = new Text(shlValoresDasVariveis, SWT.BORDER);
		inputSuporteFim.addListener(SWT.Verify, new Listener() {
			public void handleEvent(Event e) {
				String string = e.text;
				char[] chars = new char[string.length()];
				string.getChars(0, chars.length, chars, 0);
				for (int i = 0; i < chars.length; i++) {
					if (!('0' <= chars[i] && chars[i] <= '9') && (chars[i] != '-')) {
						e.doit = false;
						return;
					}
				}
			}
		});
		inputSuporteFim.setBounds(192, 44, 76, 21);
		
		inputNucleoInicio = new Text(shlValoresDasVariveis, SWT.BORDER);
		inputNucleoInicio.addListener(SWT.Verify, new Listener() {
			public void handleEvent(Event e) {
				String string = e.text;
				char[] chars = new char[string.length()];
				string.getChars(0, chars.length, chars, 0);
				for (int i = 0; i < chars.length; i++) {
					if (!('0' <= chars[i] && chars[i] <= '9') && (chars[i] != '-')) {
						e.doit = false;
						return;
					}
				}
			}
		});
		inputNucleoInicio.setBounds(83, 79, 76, 21);
		
		inputNucleoFim = new Text(shlValoresDasVariveis, SWT.BORDER);
		inputNucleoFim.addListener(SWT.Verify, new Listener() {
			public void handleEvent(Event e) {
				String string = e.text;
				char[] chars = new char[string.length()];
				string.getChars(0, chars.length, chars, 0);
				for (int i = 0; i < chars.length; i++) {
					if (!('0' <= chars[i] && chars[i] <= '9') && (chars[i] != '-')) {
						e.doit = false;
						return;
					}
				}
			}
		});
		inputNucleoFim.setBounds(192, 79, 76, 21);
		
		Label lblA = new Label(shlValoresDasVariveis, SWT.NONE);
		lblA.setText("a");
		lblA.setBounds(175, 44, 11, 15);
		
		btnAdicionar = new Button(shlValoresDasVariveis, SWT.NONE);
		btnAdicionar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String nome = inputNome.getText();
				String inicioSuporte = inputSuporteInicio.getText();
				String fimSuporte = inputSuporteFim.getText();
				String inicioNucleo = inputNucleoInicio.getText();
				String mensagem = "";
				
				if(nome == null || nome.equals("")){
					mensagem += "o Nome é obrigatório; " + quebraLinha;
				}
				
				if(valorComNomeDuplicado(nome)){
					mensagem += "Não podem existir valores com nome duplicado; " + quebraLinha;
				}
				
				if (inicioSuporte == null || inicioSuporte.equals("")){
					mensagem += "o início do Suporte é obrigatório; " + quebraLinha;
				}
				
				if(fimSuporte == null || fimSuporte.equals("")){
					mensagem += "o fim do Suporte é obrigatório; " + quebraLinha;
				}
				
				if(inicioNucleo == null || inicioNucleo.equals("")){
					mensagem += "o início do Núcleo é obrigatório; " + quebraLinha;
				}
				
				if(!mensagem.equals("")){
					MessageBox dialog = new MessageBox(shlValoresDasVariveis, SWT.ICON_ERROR | SWT.OK | SWT.CANCEL);
					dialog.setText("Campos Obrigatórios");
					dialog.setMessage(mensagem);
					dialog.open();
				} else {
					Valor valor = new Valor();
					valor.setNome(nome);
					valor.setInicioSuporte(Integer.parseInt(inicioSuporte));
					valor.setFimSuporte(Integer.parseInt(fimSuporte));
					valor.setInicioNucleo(Integer.parseInt(inicioNucleo));
					if(!inputNucleoFim.getText().equals("")){
						valor.setFimNucleo(Integer.parseInt(inputNucleoFim.getText()));
					}
					listaValores.add(valor.getNome());
					variavel.getValores().add(valor);
				}
			}

			private boolean valorComNomeDuplicado(String nome) {
				for (Valor valor : variavel.getValores()) {
					if(valor.getNome().equals(nome)){
						return true;
					}
				}
				return false;
			}
		});
		btnAdicionar.setBounds(131, 118, 75, 25);
		btnAdicionar.setText("Adicionar");
		
		listaValores = new List(shlValoresDasVariveis, SWT.BORDER);
		listaValores.setBounds(293, 19, 141, 215);
		
		btnExcluir = new Button(shlValoresDasVariveis, SWT.NONE);
		btnExcluir.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String[] valorSelecionado = listaValores.getSelection();
				java.util.List<Valor> listaValoresRemovidos = new ArrayList<Valor>();
				for (String nomeValor : valorSelecionado) {
					for (Valor valor : variavel.getValores()) {
						if (valor.getNome().equals(nomeValor)) {
							listaValoresRemovidos.add(valor);
							listaValores.remove(listaValores.getSelectionIndex());
						}
					}
				}
				variavel.getValores().removeAll(listaValoresRemovidos);
			}
		});
		btnExcluir.setBounds(326, 240, 75, 25);
		btnExcluir.setText("Excluir");
		
		btnX = new Button(shlValoresDasVariveis, SWT.NONE);
		btnX.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlValoresDasVariveis.close();
			}
		});
		btnX.setBounds(425, 1, 19, 15);
		btnX.setText("X");
		
		shlValoresDasVariveis.setLocation(getParent().getLocation());
		
		populaListaValores();
	}

	private void populaListaValores() {
		if(!variavel.getValores().isEmpty()){
			for (Valor valor : variavel.getValores()) {
				listaValores.add(valor.getNome());
			}
		}
	}

	public Variavel getVariavel() {
		return variavel;
	}

	public void setVariavel(Variavel variavel) {
		this.variavel = variavel;
	}
}
