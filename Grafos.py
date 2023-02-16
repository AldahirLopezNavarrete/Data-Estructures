import networkx as nx
import numpy as np
import heapdict as hd 

class Grafo:
  def __init__(self):
    self.G={}
    self.visitado={}
  
  def inserta_dirigido(self,v1,v2=None,peso=None):
    if v1 not in self.G.keys():
      self.G[v1]={}
      if v2 is not None:
        self.G[v1][v2]=peso
    else:
      if v2 is not None and v2 not in self.G[v1]:
        self.G[v1][v2]=peso

  def inserta(self,v1,v2,peso=None):
    self.inserta_dirigido(v1,v2,peso)
    self.inserta_dirigido(v2,v1,peso)

  
  def __recorridoProfundidad(self,actual,lista):
    if actual is None:
      return
    if self.visitado[actual]:
      return
    self.visitado[actual]=True
    lista+=[actual]
    for vecino in self.G[actual]:
      self.__recorridoProfundidad(vecino,lista)

  def DFS(self):
## visitado = [False for v in G]
    for v in self.G:
      self.visitado[v]=False
    lista=[]
    for v in self.visitado:
      if not self.visitado[v]:
        self.__recorridoProfundidad(v,lista)
    return lista

  def __recorridoAnchura(self, actual, cola):
    if actual is None:
        return
    if self.visitado[actual]:
        return
    self.visitado[actual]=True
    cola.append(actual)

  def BFS(self):
    for v in self.G:
      self.visitado[v]=False
    queue=[]
    aux = list(self.visitado.keys())
    primero=aux.pop(0)
    queue.append(primero)
    self.visitado[primero]=True
    lista=[]
    while queue:
        m = queue.pop(0)
        lista.append(m)
        for vecino in self.G[m]:
            self.__recorridoAnchura(vecino, queue)
    return lista

  def Prim(self,v_ini):
    key=hd.heapdict()
    papa={}
    for v in self.G:
      key[v]=np.inf
      papa[v]=None
    key[v_ini]=0 

    while len(key.keys())>0:
      nodo=key.popitem()[0]
      for vecino in self.G[nodo].keys():
        if vecino in key.keys() and self.G[nodo][vecino] < key[vecino]:
          key[vecino]=self.G[nodo][vecino]
          papa[vecino]=nodo
    return papa

  def Dijkstra(self,v_ini):
    key=hd.heapdict()
    papa={}
    for v in self.G:
      key[v]=np.inf
      papa[v]=None
    key[v_ini]=0
    while len(key.keys())>0:
      temp=key.popitem()
      nodo=temp[0]
      valor_ruta=temp[1]
      for vecino in self.G[nodo].keys():
        if vecino in key.keys() and valor_ruta+self.G[nodo][vecino] < key[vecino]:
          key[vecino]=valor_ruta+self.G[nodo][vecino]
          papa[vecino]=nodo
    return papa

  def profHamiltoniano(self,actual,recorrido):
    if(visitado[actual]):
      return False
    visitado[actual]=True
    recorrido.append(actual)
    if(len(recorrido)==len(G)):
      return True
    for u in G:
      res=profHamiltoniano(u,recorrido)
      if res:
        break
    visitadodo[actual]=False
    recorrido.pop(actual)
    return res


  def Diametro(self):
       for v in self.G:
           self.visitado[v]=False
       distancias = []
       for v in self.G:
           distancias.append(self.distancia(v))
       if 0 in distancias:
           return "infinito"
       return max(distancias)

       
  def distancia(self, actual):
       cola=[]
       cola.append((actual,0))
       self.visitado[actual] = True
       cont = 0
       while cola:
           tupla = cola.pop(0)
           nodo = tupla[0]
           cont = tupla[1]+1
           for vecino in self.G[nodo]:
               if not self.visitado[vecino]:
                   cola.append((vecino,cont))
                   self.visitado[vecino]=True
       return cont

