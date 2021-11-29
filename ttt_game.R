rm(list=ls())







assembleBoard<-function(){
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

assembleBoard()

changeT<-function(){
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

      if(WL(x,y)==turn) {
        gameOver <<- T
        ai()
        assembleBoard()
        return(turn)
      } 
    changeT()
    if(turn=='o') {
      ai()
      
    }
    
      
    }
  } 
  return(board)
  
}




#input is last played move
WL<<-function(x,y){
# for debugging
  # x<<-x
  # y<<-y
  

  
  diag<-function(y,x){
    
    diagUR<<-function(x,y){
      if((x<depth)&&(y<depth)){
        if(board[y+1,x+1]==turn){
          return(1+diagUR(x+1,y+1))
        } else {
          return(0)
        }} else {
          return(0)
        } }
    
    diagDR<<-function(x,y){
      if((x<depth)&&(y>1)){
        if(board[y-1,x+1]==turn){
          return(1+diagDR(x+1,y-1))
        } else {
          return(0)
        }} else {
          return(0)
        } }
    
    diagUL<<-function(x,y){
      if((x>1)&&(y<depth)){
        if(board[y+1,x-1]==turn){
          return(1+diagUL(x-1,y+1))
        } else {
          return(0)
        }} else {
          return(0)
        } }
    
    diagDL<<-function(x,y){
      if((x>1)&&(y>1)){
        if(board[y-1,x-1]==turn){
          return(1+diagDL(x-1,y-1))
        } else {
          return(0)
        }} else {
          return(0)
        } }

    
    URDLconn <<- diagUR(x,y) + diagDL(x,y)
   DRULconn<<- diagDR(x,y) + diagUL(x,y)

    if((URDLconn>1)||(DRULconn>1)) {

     return(2)
    } else {
      return(0)
    }

  }
  
  lat<-function(y,x) {

    
    latD<<-function(x,y){
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
    
    latL<<-function(x,y){
      if (x>1) {
        if(board[y,x-1]==turn) {
          return(1+latL(x-1,y))
        } else {
          return(0)
        }
      } else {
        return(0)
      }
    }
    

    
    latR<<-function(x,y){
      if (x<depth) {
        if(board[y,x+1]==turn) {
          return(1+latR(x+1,y))
        } else {
          return(0)
        }
      } else {
        return(0)
      }
    }
    
    latU<<-function(x,y){
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
    return(turn)
    
  } else {
    return(0)
  }
  
}

# startGame<-function(){
#   assembleBoard()
#   while(gameOver==F) {
#     loc<-readline(prompt = "Please enter move in format of x,y:  ")
#     loc<-strsplit(loc,",")
#     x<-as.integer(loc[[1]][1])
#     y<-x<-as.integer(loc[[1]][2])
#     move(x,y)
#     board
#     return(board)
#   }
# }
# 
# startGame()


test<-function(){
assembleBoard()
move(2,2)
move(1,1)
move(1,3)
move(3,3)
move(3,1)
board

#wins
WL(2,2)
WL(1,3)
WL(3,1)

#loss
changeT()
WL(1,1)
WL(3,3)


assembleBoard()
move(1,1)
move(2,1)
move(2,2)
move(1,2)
move(3,3)
board

#wins

WL(2,2)
WL(1,1)
WL(3,3)

#loss
changeT()
WL(2,1)
WL(1,2)

assembleBoard()
move(2,2)
move(1,1)
move(1,2)
move(3,3)
move(3,2)
board

#wins
WL(3,2)
WL(2,2)
WL(1,2)

#loss
changeT()
WL(1,1)
WL(3,3)





assembleBoard()
move(1,3)
move(2,2)
move(1,1)
move(2,1)
move(3,3)
move(2,3)
board

#win

WL(2,3)
WL(2,2)
WL(2,1)

#loss
changeT()
WL(1,1)
WL(3,3)



assembleBoard()
move(2,1)
move(2,2)
move(1,1)
move(1,3)
move(3,3)
move(3,1)
board

#wins
WL(2,2)
WL(1,3)
WL(3,1)

#loss
changeT()
WL(1,1)
WL(3,3)


assembleBoard()
move(3,2)
move(1,1)
move(2,1)
move(2,2)
move(1,2)
move(3,3)
board

#wins

WL(2,2)
WL(1,1)
WL(3,3)

#loss
changeT()
WL(2,1)
WL(1,2)

assembleBoard()
move(3,1)
move(2,2)
move(1,1)
move(1,2)
move(3,3)
move(3,2)
board

#wins
WL(3,2)
WL(2,2)
WL(1,2)

#loss
changeT()
WL(1,1)
WL(3,3)





assembleBoard()
move(2,2)
move(1,1)
move(2,1)
move(3,3)
move(2,3)
board

#win

WL(2,3)
WL(2,2)
WL(2,1)

#loss
changeT()
WL(1,1)
WL(3,3)
}






#-------------------------------------------------------------------
#start of nn






fullSetWE<-function(){
  setwd("C:/Users/ethan/Desktop/Projects")
  dir.create("nnResources")
  setwd("C:/Users/ethan/Desktop/Projects/nnResources")
  file.create("symArray.csv")
  events<<-c("b","v","x","o")
  vals<-c(10,10,10,10)
  symArray<<-data.frame(events,vals)
  write.csv(symArray,'symArray.csv')
}

halfSetWE<-function() {
  setwd("C:/Users/ethan/Desktop/Projects/nnResources")
  events<<-c("b","v","x","o")
}


availMoves<-function(){
which(board==" ")

  #board[2+depth]
}

symEval<-function(n){
l<-function(n){
  
  if(n>depth)
  {
    n<-n-depth
    return(board[n])
    
  } else {
    return("v")
  }
  
}

r<-function(n){
  
  if(n<=(depth^2)-depth)
  {
    n<-n+depth
    return(board[n])
    
  } else {
    return("v")
  }
  
}

u<-function(n){
  
  if(n>1)
  {
    n<-n-1
    return(board[n])
    
  } else {
    return("v")
  }
  
}

d<-function(n){
  
  if(n<depth^2)
  {
    n<-n+1
    return(board[n])
    
  } else {
    return("v")
  }
  
}

dl<-function(n){
  
  if((n<depth^2)&&(n>depth))
  {
    n<-n+1-depth
    return(board[n])
    
  } else {
    return("v")
  }
  
}

dr<-function(n){
  
  if((n<depth^2)&&(n<=(depth^2)-depth))
  {
    n<-n+1+depth
    return(board[n])
    
  } else {
    return("v")
  }
  
}

ul<-function(n){
  
  if(((n%%depth)>1)&&(n>depth))
  {
    n<-n-1-depth
    return(board[n])
    
  } else {
    return("v")
  }
  
}

ur<-function(n){
  
  if(((n%%depth)>1)&&(n<=(depth^2)-depth))
  {
    n<-n-1+depth
    return(board[n])
    
  } else {
    return("v")
  }
  
}


return(c(u(n),d(n),l(n),r(n),ur(n),ul(n),dr(n),dl(n)))
}

evalQuant<-function(quant){
  v<-function(){
    if(is.na(quant["v"])){
      return(0)
    }else {
      return(as.numeric(quant["v"]))
    }
  }
  
  b<-function(){
    if(is.na(quant[" "])){
      return(0)
    }else {
      return(as.numeric(quant[" "]))
    }
  }
  
  vx<-function(){
    if(is.na(quant["x"])){
      return(0)
    }else {
      return(as.numeric(quant["x"]))
    }
  }
  
  vo<-function(){
    if(is.na(quant["o"])){
      return(0)
    }else {
      return(as.numeric(quant["o"]))
    }
  }
  return(data.frame(b()/8,v()/8,vx()/8,vo()/8))
}


#still have to work on making connections functions for 2x and 2o



prioritize<-function(){
  


    symVal<-function(n) {
  newVals<-symEval(n)
  quant<<-table(newVals)
  origVals<-read.csv(file='symArray.csv')
  origVals<-as.numeric(origVals$vals)
  coe<-as.numeric(evalQuant(quant)*8)
  
  tot<-origVals[1]*coe[1]
  tot<-tot + origVals[2]*coe[4]
  tot<-tot + origVals[3]*coe[2]
  tot<-tot + origVals[4]*coe[3]
  return(tot)

    }
    
    origVals<-read.csv(file='symArray.csv')
    avail<-c(which(board==" "))
    symPriors<-lapply(avail,symVal)
    moves<-avail[which.max(symPriors)]
    
    ba<-moves/depth
    x<-(ba-as.integer(ba))*depth
    y<-ceiling(ba)
    if(x==0){
      x<-depth
    }
 
    moves<-c(x,y)
    
    return(moves)
  
  
}

ai<<-function(){
  #ai is only set to play as o
  if(gameOver!=T) {
 det<-prioritize()
  aix<<-as.numeric(det[1])
  aiy<<-as.numeric(det[2])
  move(aix,aiy) 
  } else {
  if(gameOver==T){
    if (turn=='o'){
      win(moves)
    } else {
    loss(moves)
  }
  }
  }
  
}


win<<-function(n){
  
  newVals<-symEval(n)
  quant<-table(newVals)
  origVals<-read.csv(file='symArray.csv')
  adjustors<- evalQuant(quant)
  adjustors<-as.numeric(adjustors)
  origVals$vals<-origVals$vals+adjustors
  symArray$vals<-origVals$vals
  write.csv(symArray,file='symArray.csv')
  

  
}

loss<<-function(n){
  
  newVals<-symEval(n)
  quant<-table(newVals)
  origVals<-read.csv(file='symArray.csv')
  origVals<-as.numeric(origVals$vals)
  adjustors<- evalQuant(quant)
  adjustors<-as.numeric(adjustors)
  origVals<-origVals-adjustors
  symArray$vals<-origVals
  write.csv(symArray,'symArray.csv')
  
  
  
}

ai()

