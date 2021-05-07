# -*- coding: utf-8 -*-
"""
Created on Wed May  5 15:58:45 2021

@author: 2rome
"""
import math

#Función read_file implementada por Hugo Romero
#LEE UN FICHERO DE TEXTO PLANO Y LO DEVUELVE COMO STRING
def read_file(file):
    with open(file, 'r') as reader:
        content = reader.read().splitlines()
        lines = [x.lstrip() for x in content if x != ""]
        return lines

#Función angle(tuple, tuple) implementada por Hugo Romero
#CALCULA EL ANGULO (GONIOMÉTRICO) ENTRE EL EJE X Y EL VECTOR QUE FORMAN LOS PUNTOS DE ENTRADA
def angle(point, center):
    vector_1 = [point[0]-center[0], point[1]-center[1]]
    angle = math.atan2(vector_1[1], vector_1[0]) * 180 / math.pi
    return round(angle,4)

def save_string(filename, output):
    with open("./TSP_generados/" + filename, 'w') as file:
        file.write(output)
    return