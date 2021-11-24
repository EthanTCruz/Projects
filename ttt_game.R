rm(list=ls())





assembleBoard<<-function(){
  #commented out code is for scaling board to different sizes
  #  depth <<- as.integer(readline(prompt = "Please enter grid square length: ") ) 

  depth<<-3
  dSq <<- depth^2
  board<<-array(dim = c(depth,depth))

  #TRUE = x, FALSE = o
  turn<<-"x"
  gameOver<<-F
  URDLconn<<-0
  DRULconn<<-0
  LRconn<<-0
  UDconn<<-0
  
  board[is.na(board)] <- " "
  board <<- board
  
  
}

changeT<<-function(){
  if (turn == "x"){
    turn <<- 'o'
  } else {
    turn <<- 'x'
  }
}




move<<-function(x,y){
  #change to while function with prompt once win condition is established
  if (gameOver == F) {
    
    if (board[x,y]==" "){
      board[x,y] <<- turn
      if(WL(x,y)==1) {
        paste(turn," Wins")
      } else {
      changeT()
      }
    }
  }
  
}

#input is last played move
WL<-function(x,y){

  x<-x
  y<-y
  
  
  diag<-function(x,y){
    
    
    diagUR<-function(x,y){
      if((x<depth)&&(y<depth)){
        if(board[y+1,x+1]==turn){
          return(1+diagUR(x+1,y+1))
        } else {
          return(0)
        }} else {
          return(0)
        } }
    
    diagDR<-function(x,y){
      if((x<depth)&&(y>1)){
        if(board[y-1,x+1]==turn){
          return(1+diagUR(x+1,y-1))
        } else {
          return(0)
        }} else {
          return(0)
        } }
    
    diagUL<-function(x,y){
      if((x>1)&&(y<depth)){
        if(board[y+1,x-1]==turn){
          return(1+diagUR(x-1,y+1))
        } else {
          return(0)
        }} else {
          return(0)
        } }
    
    diagDL<-function(x,y){
      if((x>1)&&(y>1)){
        if(board[y-1,x-1]==turn){
          return(1+diagUR(x-1,y-1))
        } else {
          return(0)
        }} else {
          return(0)
        } }
    
    
    URDLconn <<- diagUR(x,y) + diagDL(x,y)
   DRULconn<<- diagDR(x,y) + diagUL(x,y)
   retVal<-0
    if((URDLconn>1)||(DRULconn>1)) {

      retval<-2
    } 
   return(retVal)
  }
  
  lat<-function(x,y) {

    
    latD<-function(x,y){
      if (y>1) {
        if(board[y-1,x]==turn) {
          return(1+latD(x,y-1))
        } else {
          return(0)
        }
      } else {
        return(0)
      }
    }
    
    latL<-function(x,y){
      if (x>1) {
        if(board[y,x-1]==turn) {
          return(1+latU(x-1,y))
        } else {
          return(0)
        }
      } else {
        return(0)
      }
    }
    
    latR<-function(x,y){
      if (x<depth) {
        if(board[y,x+1]==turn) {
          return(1+latU(x+1,y))
        } else {
          return(0)
        }
      } else {
        return(0)
      }
    }
    
    latU<-function(x,y){
      if (y<depth) {
        if(board[y+1,x]==turn) {
          return(1+latU(x,y+1))
        } else {
          return(0)
        }
      } else {
        return(0)
      }
    }
    
    
    
    
    UDconn <<- latU(x,y) + latD(x,y)
    LRconn <<- latR(x,y) + latL(x,y)
    
    
    #if want to adjust scale of win vector change 2 to depth-1
    if ((UDconn>1)||(LRconn>1)) {
      
      return(2)
    } else {
      
      return(0)
    }
    
    
  }
  if ((lat(x,y) > 1)||(diag(x,y) > 1)) {
    return(1)
    
  } else {
    return(0)
  }
  
}



testL<<- function(){
  assembleBoard()
move(2,2)
move(3,3)
move(1,2)
move(1,3)
move(3,2)


assembleBoard()
move(2,2)
move(2,3)
move(3,3)
move(2,1)
board
move(1,1)


}


assembleBoard()

move(2,2)
move(2,3)
move(3,3)


move(2,1)

move(1,1)
board

diag(1,1)






