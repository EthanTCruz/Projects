rm(list=ls())

 



assembleBoard<-function(){
  #commented out code is for scaling board to different sizes
  #  depth <<- as.integer(readline(prompt = "Please enter grid square length: ") ) 
  depth<<-3
   dSq <<- depth^2
  board<<-array(dim = c(depth,depth))
  board
#TRUE = x, FALSE = o
  turn<<-"x"
  gameOver<<-F
  board

  
  
}

changeT<-function(){
  if (turn == "x"){
    turn <<- 'o'
  } else {
    turn <<- 'x'
  }
}

x = NA
is.na(x)


move<-function(x,y){
  #change to while function with prompt once win condition is established
  if (gameOver == F) {
    
    if (is.na(board[x,y])){
      board[x,y] <<- turn
      changeT()
    }
  }
  
}

#input is last played move
WL<-function(x,y){
  

  
diag<-function(x,y){
  
  changeT()
  
  diagUR<-function(x,y){
    if((x<d)&&(y<d)){
    if(board[x+1,y+1]==turn){
      return(1+diagUR(x+1,y+1))
    } else {
      return(0)
    }} else {
      return(0)
      } }
  
    diagDR<-function(x,y){
      if((x<d)&&(y>0)){
        if(board[x+1,y-1]==turn){
          return(1+diagUR(x+1,y+1))
        } else {
          return(0)
        }} else {
          return(0)
        } }
    
    diagUL<-function(x,y){
      if((x>0)&&(y<d)){
        if(board[x-1,y+1]==turn){
          return(1+diagUR(x+1,y+1))
        } else {
          return(0)
        }} else {
          return(0)
        } }
    
    diagDL<-function(x,y){
      if((x>d)&&(y>0)){
        if(board[x-1,y-1]==turn){
          return(1+diagUR(x+1,y+1))
        } else {
          return(0)
        }} else {
          return(0)
        } }

 
  URDLconn <- diagUR(x,y) + diag(x,y)
  DRULconn<-diagDR(x,y) + diag(x,y)
  if((URDLconn>2)||(DRULconn>2)) {
    changeT()
    return(3)
  } else {
  changeT()
  return(0)
  }
}

lat<-function(x,y) {
  changeT()
  
  latU<-function(x,y){
    if (y<d) {
      if(board[x,y+1]) {
        return(1+latU(x,y+1))
      } else {
        return(0)
      }
    } else {
      return(0)
    }
  }
  
  latD<-function(x,y){
    if (y>0) {
      if(board[x,y-1]) {
        return(1+latU(x,y-1))
      } else {
        return(0)
      }
    } else {
      return(0)
    }
  }
  
  latR<-function(x,y){
    if (x<d) {
      if(board[x+1,y]) {
        return(1+latU(x+1,y))
      } else {
        return(0)
      }
    } else {
      return(0)
    }
  }
  
  latL<-function(x,y){
    if (x>0) {
      if(board[x-1,y]) {
        return(1+latU(x-1,y))
      } else {
        return(0)
      }
    } else {
      return(0)
    }
  }
  
  

  
  UDconn <- latU(x,y) + latD(x,y)
  LRconn <- latR(x,y) + latL(x,y)

  
  #if want to adjust scale of win vector change 2 to depth-1
  if ((UDconn>2)||(LRconn>2)) {
    changeT()
    return(3)
  } else {
    changeT()
    return(0)
  }
  

}

if ((lat(x,y) > 2)||(diag(x,y) > 2)) {
  turn
  "wins"
}

}


assembleBoard()
3
move(2,2)
move(3,3)
board


