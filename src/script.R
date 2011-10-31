speedLimit = 6 # In knots
resolution = .5 # In degrees

library(sp)
library(rgdal)
library(MASS)
library(fields)

path.to.data="/Users/Anthony/coralfish-incanter/src/"
 
bt=read.table(file=paste(path.to.data,"vms_trial_data.csv",sep=""),sep=",",header=T)

bt=bt[bt$lon< -10,]

bt=bt[bt$speed<speedLimit,]

resolucion = c(resolution,resolution)
puntoSO = c(min(bt$lon),min(bt$lat))
puntoSO = puntoSO - resolucion/2
nx = ceiling(diff(range(bt$lon)) / resolucion[1])+1
ny = ceiling(diff(range(bt$lat)) / resolucion[2])+1
n.celdas = c(nx,ny)
n.tot.celdas = nx*ny
topo=GridTopology(puntoSO, resolucion, n.celdas)
grilla=SpatialGrid(topo,CRS("+proj=longlat + ellps=WGS84"))

coordinates(bt) = ~lon+lat
proj4string(bt) = CRS("+proj=longlat + ellps=WGS84")
ovr=overlay(grilla,bt)
cpue=rep(0,times=n.tot.celdas)
tab=table(ovr)
cpue[as.numeric(names(tab))]=tab
cpue=data.frame(cpue=cpue)
grilla.cpue=SpatialGridDataFrame(grilla,cpue)

#par(mfrow=c(2,2))
#h=truehist(grilla.cpue$cpue,h=20,xlim=c(0,quantile(grilla.cpue$cpue,prob=0.99)),col="gray",xlab="Effort - VMS records per cell",prob=F)
#cols=c("#FFFFFF",tim.colors(99))
#image(grilla.cpue,col=cols,axes=T)
#points(bt,pch=".",axes=T,col="gray",cex=0.3)
      