package project;

class NotVaildAutosarFileException extends Exception {
    public NotVaildAutosarFileException(String s){
        super(s);
    }
}

class EmptyAutosarFileException extends Exception{
	public EmptyAutosarFileException(String s) {
		super(s);
	}
}

class FileNotFound extends Exception{
	public FileNotFound (String s) {
		super(s);
	}
}

