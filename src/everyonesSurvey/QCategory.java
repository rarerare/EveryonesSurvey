package everyonesSurvey;

public enum QCategory {
	samc("radio","sachoices","saSelections",true)
	, mamc("checkbox","machoices","maSelections",true)
	, fr("text",null,"frAnswers",false)
	, number("number",null,"numAnswer",false);
	
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
