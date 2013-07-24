package th.co.aerothai.document.datamodel;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import th.co.aerothai.document.model.CategoryAdmin;

public class MemberDataModel extends ListDataModel<CategoryAdmin> implements SelectableDataModel<CategoryAdmin>{
	
	public MemberDataModel(){
		
	}
	
	public MemberDataModel(List<CategoryAdmin> data){
		super(data);
	}

	@Override
	public CategoryAdmin getRowData(String arg0) {
		// TODO Auto-generated method stub
		List<CategoryAdmin> memebrs = (List<CategoryAdmin>) getWrappedData();
		
		for(CategoryAdmin member : memebrs){
			if(member.getPersonalInfo().getSTAFFCODE().equals(arg0)){
				return member;
			}		
		}
		return null;
	}

	@Override
	public Object getRowKey(CategoryAdmin arg0) {
		// TODO Auto-generated method stub
		return arg0.getPersonalInfo().getSTAFFCODE();
	}
}
