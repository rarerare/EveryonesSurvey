package everyonesSurvey;

public enum QCategory {
	
	samc("radio","sa_option","sa_selection",true)
	, mamc("checkbox","ma_option","ma_selection",true)
	, fr("text",null,"fr_ans",false)
	, number("number",null,"num_ans",false);
	
	private String inputType;
	private String optTable;
	private String ansTable;
	private boolean finAns;
	QCategory( String inputType,String optTable,String ansTable,boolean finAns){
		this.inputType=inputType;
		this.optTable=optTable;
		this.ansTable=ansTable;
		this.finAns=finAns;
	}
	public String getInputType(){
		return this.inputType;
	}
	public String getOptTable(){
		if(this==samc||this==mamc){
			return optTable;
		}else{
			throw new NullPointerException();
		}
	}
	public String getAnsTable(){
		return ansTable;
	}
	public boolean isFinAns(){
		return finAns;
	}
}
