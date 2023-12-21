package schedulermicroservice.exception;

public class RouteIdDoesNotExistException extends RuntimeException{
        String str;
        public RouteIdDoesNotExistException(){
            super();
        }
         public RouteIdDoesNotExistException(String str){
            super(str);
            this.str=str;
        }

}
