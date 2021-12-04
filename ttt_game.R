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
      if(length(which(board==" "))==0)
      {
        assembleBoard()
        return("draw")
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
#     move(y,x)
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

  if(dir.exists("../nnResources")) {
    file.create("symArray.csv")
    file.create("connArray.csv")
    connEvents<-c("b","o","x")
    connVals<-c(0,0,0)
    events<<-c("b","v","x","o")
    vals<-c(0,0,0,0)
    symArray<-data.frame(events,vals)
    connArray<-data.frame(connEvents,connVals)
    write.csv(connArray,'connArray.csv')
    write.csv(symArray,'symArray.csv')
  } else {
  dir.create("nnResources")
  setwd("nnResources")
  file.create("symArray.csv")
  file.create("connArray.csv")
  connEvents<-c("b","o","x")
  connVals<-c(0,0,0)
  events<<-c("b","v","x","o")
  vals<-c(0,0,0,0)
  symArray<-data.frame(events,vals)
  connArray<-data.frame(connEvents,connVals)
  write.csv(connArray,'connArray.csv')
  write.csv(symArray,'symArray.csv')
  }
}



availMoves<-function(){
which(board==" ")

  #board[2+depth]
}

priorConnEval<-function(n){
  

  
  connL<-function(n){
    or<-l(n);
    if(or==l(n-depth)){
      return(or)
    }
  }
  connR<-function(n){
    or<-r(n);
    if(or==r(n+depth)){
      return(or)
    }
  }
  connU<-function(n){
    or<-u(n);
    if(or==u(n-1)){
      return(or)
    }
  }
  connD<-function(n){
    or<-d(n);
    if(or==d(n+1)){
      return(or)
    }
  }
  connUR<-function(n){
    or<-ur(n);
    if(or==ur(n-1+depth)){
      return(or)
    }
  }
  connDR<-function(n){
    or<-dr(n);
    if(or==dr(n+1+depth)){
      return(or)
    }
  }
  connUL<-function(n){
    or<-ul(n);
    if(or==ul(n-1-depth)){
      return(or)
    }
  }
  connDL<-function(n){
    or<-dl(n);
    if(or==dl(n+1-depth)){
      return(or)
    }
  }
  

  conns<-c(connU(n),connD(n),connL(n),connR(n),connUR(n),connUL(n),connDR(n),connDL(n))
  conns<-conns[conns!="v"]
  
  return(conns)
}

#if sym functions work globally delete this


symEval<-function(n){
l<<-function(n){
  
  if(n>depth)
  {
    n<-n-depth
    return(board[n])
    
  } else {
    return("v")
  }
  
}

r<<-function(n){
  
  if(n<=(depth^2)-depth)
  {
    n<-n+depth
    return(board[n])
    
  } else {
    return("v")
  }
  
}

u<<-function(n){
  
  if((n>1)&&(n%%depth!=1))
  {
    n<-n-1
    return(board[n])
    
  } else {
    return("v")
  }
  
}

d<<-function(n){
  
  if((n<depth^2)&&(n%%depth!=0))
  {
    n<-n+1
    return(board[n])
    
  } else {
    return("v")
  }
  
}

dl<<-function(n){
  
  if((n<depth^2)&&(n%%depth!=0)&&(n>depth))
  {
    n<-n+1-depth
    return(board[n])
    
  } else {
    return("v")
  }
  
}

dr<<-function(n){
  
  if((n<depth^2)&&(n%%depth!=0)&&(n<=(depth^2)-depth))
  {
    n<-n+1+depth
    return(board[n])
    
  } else {
    return("v")
  }
  
}

ul<<-function(n){
  
  if((n%%depth!=1)&&(n>depth))
  {
    n<-n-1-depth
    return(board[n])
    
  } else {
    return("v")
  }
  
}

ur<<-function(n){
  
  if((n>1)&&(n%%depth!=1)&&(n<=(depth^2)-depth))
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
  return(data.frame(b()/9,v()/((depth+1)^2),vx()/9,vo()/9))
}






prioritize<-function(){
  


    symVal<-function(n) {
  newVals<-symEval(n)
  quantSym<<-table(newVals)
  origVals<-read.csv(file='symArray.csv')
  origVals<-as.numeric(origVals$vals)
  coe<-as.numeric(evalQuant(quantSym))
  
  tot<-origVals[1]*coe[1]
  tot<-tot + origVals[2]*coe[4]
  tot<-tot + origVals[3]*coe[2]
  tot<-tot + origVals[4]*coe[3]
  return(tot)

    }
    
    connVal<-function(n) {
      newVals<-priorConnEval(n)
      quantConn<<-table(newVals)
      origVals<-read.csv(file='connArray.csv')
      origVals<-as.numeric(origVals$connVals)
      coe<-as.numeric(evalQuant(quantConn))
      coe<-coe[-2]
      
      tot<-coe[1] * origVals[1]
      tot<-tot +coe[2] * origVals[2]
      tot<-tot + coe[3] * origVals[3]
      return(tot)
      
    }
    
    process<-function(){
      avail<-c(which(board==" "))
      symPriors<-lapply(avail,symVal)
      connPriors<-lapply(avail,connVal)
      connPriors<-as.numeric(connPriors)^2
      symPriors <- as.numeric(symPriors)
      calc<-connPriors+symPriors
      return(calc)
    }
    
    calc<-process()
    origVals<-read.csv(file='symArray.csv')
    avail<-c(which(board==" "))
    symPriors<-lapply(avail,symVal)
    moves<<-avail[which.max(calc)]
    
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
  aix<-ceiling(det[1])
  aiy<-ceiling(det[2])
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
  quantSym<-table(newVals)
  origVals<-read.csv(file='symArray.csv')
  symArray<-read.csv(file='symArray.csv')
  adjustors<- evalQuant(quantSym)
  adjustors<-as.numeric(adjustors)
  origVals$vals<-origVals$vals-adjustors
  symArray$vals<-origVals$vals
  vals<-c(origVals$vals)
  events<-c(symArray$events)
  symArray<-data.frame(events,vals)
  write.csv(symArray,file='symArray.csv')
  
  newVals<-priorConnEval(n)
  quantConn<-table(newVals)
  origVals<-read.csv(file='connArray.csv')
  connArray<-read.csv(file='connArray.csv')
  adjustors<- evalQuant(quantConn)[-2]
  adjustors<-as.numeric(adjustors)
  origVals$connVals<-origVals$connVals-adjustors
  connArray$connVals<-origVals$connVals
  connVals<-c(origVals$connVals)
  connEvents<-c(connArray$connEvents)
  connArray<-data.frame(connEvents,connVals)
  write.csv(connArray,file='connArray.csv')
  

  
}

loss<<-function(n){
  
  newVals<-symEval(n)
  quantSym<-table(newVals)
  origVals<-read.csv(file='symArray.csv')
  symArray<-read.csv(file='symArray.csv')
  adjustors<- evalQuant(quantSym)
  adjustors<-as.numeric(adjustors)
  origVals$vals<-origVals$vals-adjustors
  symArray$vals<-origVals$vals
  vals<-c(origVals$vals)
  events<-c(symArray$events)
  symArray<-data.frame(events,vals)
  write.csv(symArray,file='symArray.csv')
  
  newVals<-priorConnEval(n)
  quantConn<-table(newVals)
  origVals<-read.csv(file='connArray.csv')
  connArray<-read.csv(file='connArray.csv')
  adjustors<- evalQuant(quantConn)[-2]
  adjustors<-as.numeric(adjustors)
  origVals$connVals<-origVals$connVals-adjustors
  connArray$connVals<-origVals$connVals
  connVals<-c(origVals$connVals)
  connEvents<-c(connArray$connEvents)
  connArray<-data.frame(connEvents,connVals)
  write.csv(connArray,file='connArray.csv')
  
  
  
}

train<<-function(){
  move(3,3)
  move(1,2)
  move(3,1)
  move(3,2)
  
  move(1,1)
  move(1,2)
  move(3,2)
  move(3,3)
  
}




