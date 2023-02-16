class NodoTrie:
  """
     Clase NodoTrie usada dentro de la clase Trie
  """
  def __init__(self):
    self.hijos={}
    #self.elemento=None # esto es si queremos usar esto como llave valor, el elemento es el valor. Ahora no lo usamos asi
    self.fin=False 


class Trie:
  """
  Descripcion de la clase
  """
  def __init__(self):
    self.raiz=NodoTrie()
    self.cont=0
    
  def inserta(self,cadena):
    """
    Parameters:
      cadena (string): Una cadena de símbolos a insertar en el Trie

      Returns:
      No regresa valor
    """
    actual=self.raiz
    for letra in cadena:
      if letra in actual.hijos.keys():
        actual=actual.hijos[letra]
      else:
        actual.hijos[letra]=NodoTrie()
        actual=actual.hijos[letra]
    if not actual.fin:
      actual.fin=True
      self.cont+=1

  def busca_iterativo(self,cadena):
    actual=self.raiz
    i=0
    encontre=True
    while encontre and i<len(cadena):
      letra=cadena[i]
      if letra in actual.hijos.keys():
        actual=actual.hijos[letra]
      else:
        encontre=False
      i+=1
    return encontre and actual.fin

  def __busca_recursivo(self,cadena,actual):
    if actual is None:
      return False
  
    if len(cadena)==0:
      return actual.fin

    res=False    

    if cadena[0] in actual.hijos.keys():
      res=self.__busca_recursivo(cadena[1:],actual.hijos[cadena[0]])

    return res
    

  def busca_recursivo(self,cadena):
    return self.__busca_recursivo(cadena,self.raiz)
  
  def __borra(self, actual, cadena):

    if len(cadena)==0:
      if actual.fin:
        self.cont-=1
        actual.fin=False
        if len(actual.hijos.keys())==0:
          return None
      return actual

    if cadena[0] in actual.hijos.keys():
      res=self.__borra(actual.hijos[cadena[0]],cadena[1:])
#??? ue hago aqui con actual
    if res is None:
      del actual.hijos[cadena[0]]
      if len(actual.hijos)==0 and actual.fin==False:
        return None
    return actual

  def borra(self, cadena):
    self.__borra(self.raiz,cadena)

  def __recorre(self,actual,palabra,contenido):
    if actual is None:
      return
    if actual.fin:
      contenido+=[palabra+"\n"]
    for hijo in actual.hijos.keys():
      self.__recorre(actual.hijos[hijo],palabra+"+"+str(hijo),contenido)
  
  def __str__(self):
    contenido=[]
    self.__recorre(self.raiz,"",contenido)
    return(" ".join(contenido))
