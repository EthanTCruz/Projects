rm(list=ls())

move<-function(x,y){
  #change to while function with prompt once win condition is established

    
    if (board[x,y]==" "){
      board[x,y] <<- turn
      } else {
        changeT()
    }
  }
  


#input is last played move
  

  
  

    
    
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
    
    
    
    
    

    
    
    #if want to adjust scale of win vector change 2 to depth-1

  
