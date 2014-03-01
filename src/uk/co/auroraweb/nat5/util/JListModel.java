package uk.co.auroraweb.nat5.util;

import java.util.List;

import javax.swing.AbstractListModel;

public class JListModel<T> extends AbstractListModel<T> {

	private static final long serialVersionUID = 1L;
	
	private List<T> data;

	    public JListModel(List<T> people) {
	        this.data = people;
	    }

	    @Override
	    public int getSize() {
	        return data.size();
	    }

	    @Override
	    public T getElementAt(int index) {
	        return data.get(index);
	    }
	
}
