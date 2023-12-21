package schedulermicroservice.exception;

public class TrainNoDoesNotExistException extends RuntimeException{
        String str;
        public TrainNoDoesNotExistException(){
            super();
        }
         public TrainNoDoesNotExistException(String str){
            super(str);
            this.str=str;
        }

}
