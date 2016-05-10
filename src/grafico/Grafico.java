package grafico;

import info.monitorenter.gui.chart.Chart2D;
import info.monitorenter.gui.chart.ITrace2D;
import info.monitorenter.gui.chart.traces.Trace2DLtd;

import java.awt.Color;

import javax.swing.JFrame;

import shell.entities.Valor;
import shell.entities.Variavel;

public class Grafico {

	public void abrirGrafico(Variavel variavel) {

		// Cria gráfico
		Chart2D chart = new Chart2D();

		for (Valor valor : variavel.getValores()) {
			ITrace2D trace = new Trace2DLtd(200);
			trace.setName(valor.getNome());
			chart.addTrace(trace);
			if (valor.getInicioNucleo() == valor.getInicioSuporte()) {
				trace.setColor(Color.MAGENTA);
				trace.addPoint(valor.getInicioSuporte(), 1);
				trace.addPoint(valor.getFimNucleo(), 1);
				trace.addPoint(valor.getFimSuporte(), 0);
			} else if (valor.getFimSuporte() == valor.getFimNucleo()) {
				trace.setColor(Color.RED);
				trace.addPoint(valor.getInicioSuporte(), 0);
				trace.addPoint(valor.getInicioNucleo(), 1);
				trace.addPoint(valor.getFimSuporte(), 1);
			} else if (valor.getInicioNucleo() == valor.getFimNucleo()) {
				trace.setColor(Color.BLUE);
				trace.addPoint(valor.getInicioSuporte(), 0);
				trace.addPoint(valor.getFimNucleo(), 1);
				trace.addPoint(valor.getFimSuporte(), 0);
			}else {
				trace.setColor(Color.GREEN);
				trace.addPoint(valor.getInicioSuporte(), 0);
				trace.addPoint(valor.getInicioNucleo(), 1);
				trace.addPoint(valor.getFimNucleo(), 1);
				trace.addPoint(valor.getFimSuporte(), 0);
			}

		}

		JFrame frame = new JFrame("Velocidade");
		frame.getContentPane().add(chart);
		frame.setSize(400, 300);

		frame.setVisible(true);

	}

}