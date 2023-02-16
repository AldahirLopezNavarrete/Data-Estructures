import numpy as np
import hashlib as hl
import random as rnd
import matplotlib.pyplot as plt

class BloomFilter:
  def __init__(self,m,k=1):

    bits=int(np.log2(m)+1)
    hexas=int(.5+bits/4)
    if k*hexas >128/4:
      try:
        raise NameError('Error de tamaño')
      except NameError:
        print('demasiado largo para nuestra funcion de Hash')
        raise

    self.m=m
    self.k=k
    self.bloom=np.array([False for i in range(m)], dtype=bool)

  def calcula_k_hashes(self,dato):
    md5=hl.md5(str(dato).encode('utf-8')).hexdigest()
    #print(md5)
    bits=int(0.5 + np.log2(self.m))
    hexas=int(1+bits/4)
    #print(bits,hexas)
    posiciones=[]
    for i in range(0,self.k*hexas,hexas):
        posiciones.append(int(md5[i:i+hexas],16)%self.m)  
    return posiciones

  def inserta(self,dato):
    posiciones=self.calcula_k_hashes(dato)
    for pos in posiciones:
      self.bloom[pos]=True

  def contains(self,dato):
    posiciones=self.calcula_k_hashes(dato)
    found=True
    i=0
    while found and i<len(posiciones):
      if not self.bloom[posiciones[i]]:
        found=False
      i=i+1
    return found
    
