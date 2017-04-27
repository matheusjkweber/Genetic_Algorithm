package Genetic_Algorithm;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;

public class ProgressAlert{
	protected Shell dialog;
    protected ProgressBar bar;
    protected int maximum;
    protected Shell parent;
    protected int progress;
    
    public ProgressAlert(Shell parent, int maximum){
    	this.parent = parent;
    	this.maximum = maximum;
    	
    }
    public void showProgressBar(){
    	dialog = new Shell(parent, SWT.APPLICATION_MODAL | SWT.TITLE); 
        dialog.setSize(400,64);
        dialog.setText("Treinando algoritmo...");
        dialog.setLayout(new FillLayout());
        bar = new ProgressBar(dialog, SWT.SMOOTH);
        
        Rectangle parentSize = dialog.getParent().getBounds();
        Rectangle shellSize = dialog.getBounds();
        int locationX = (parentSize.width - shellSize.width)/2+parentSize.x;
        int locationY = (parentSize.height - shellSize.height)/2+parentSize.y;
        dialog.setLocation(new Point(locationX, locationY));
        
        bar.setMaximum(maximum);

        dialog.open();

        final Display display = dialog.getDisplay();
        display.asyncExec(new Runnable() { //asyncExec syncExec
            @Override
            public void run() {
                try {
                	while(progress < maximum){
                		
                		bar.setSelection(progress);
                		display.sleep();
                	}
                    
                } finally {
                    dialog.close();
                }
            }
        });
        while (!dialog.isDisposed()) { 
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
    }
    
    public void setProgress(int progress){
    	this.progress = progress;
    }
    
    public int getProgress(){
    	return progress;
    }
}
