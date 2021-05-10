# -*- coding: utf-8 -*-
"""
Created on Tue May  4 12:02:52 2021

@author: 2rome
"""

from TSP import TSP
import numpy as np
from numba import njit

@njit
def min2_1_jit(arr):
    bound = 0
    for i in range(arr.shape[0]):
        minim = np.inf
        second_minim = np.inf
        for j in range(arr.shape[0]):
            if arr[i][j] > 0 and arr[i][j] < minim:
                second_minim = minim
                minim = arr[i][j]    
            elif arr[i][j] > 0 and arr[i][j] < second_minim:
                    second_minim = arr[i][j]
        bound = bound + (minim+second_minim)/2
    return bound

@njit
def min2_2_jit(arr):
    bound = 0
    for i in range(arr.shape[0]):
        A, B = np.partition(arr[i], 2)[1:3]
        bound = bound + (A+B)/2
        
    return bound


@njit
def min2_3_jit(arr):
    bound = 0
    for i in range(arr.shape[0]):
        bound = bound + np.mean(np.sort(arr[i])[1:3])
    return bound

def min2_1(arr):
    bound = 0
    for i in range(arr.shape[0]):
        minim = np.inf
        second_minim = np.inf
        for j in range(arr.shape[0]):
            if arr[i][j] > 0 and arr[i][j] < minim:
                second_minim = minim
                minim = arr[i][j]    
            elif arr[i][j] > 0 and arr[i][j] < second_minim:
                    second_minim = arr[i][j]
        bound = bound + (minim+second_minim)/2
    return bound


def min2_2(arr):
    bound = 0
    for i in range(arr.shape[0]):
        A, B = np.partition(arr[i], 2)[1:3]
        bound = bound + (A+B)/2
        
    return bound



def min2_3(arr):
    bound = 0
    for i in range(arr.shape[0]):
        bound = bound + np.mean(np.sort(arr[i])[1:3])
    return bound



if __name__ == '__main__':
    tsp = TSP()
    arr = tsp.obtener_random(1000)
    import time
    repetitions = 50
    total1 = []
    for i in range(repetitions):
        start = time.time()
        res = min2_1(tsp.graph)
        end = time.time()
        total1.append(end - start)
        print(f'{i}/50')
    print(f'Media min21 : {np.mean(total1[1:])}')
    total2 = []
    for i in range(repetitions):
        start = time.time()
        res = min2_2(tsp.graph)
        end = time.time()
        total2.append(end - start)
    print(f'Media min22: {np.mean(total2[1:])}')
    total3 = []
    for i in range(repetitions):
        start = time.time()
        res = min2_3(tsp.graph)
        end = time.time()
        total3.append(end - start)
    print(f'Media min23: {np.mean(total3[1:])}')
    total4 = []
    for i in range(repetitions):
        start = time.time()
        res = min2_1_jit(tsp.graph)
        end = time.time()
        total4.append(end - start)
    print(f'Media min21jit: {np.mean(total4[1:])}')
    total5 = []
    for i in range(repetitions):
        start = time.time()
        res = min2_2_jit(tsp.graph)
        end = time.time()
        total5.append(end - start)
    print(f'Media min22jit: {np.mean(total5[1:])}')
    total6 = []
    for i in range(repetitions):
        start = time.time()
        res = min2_3_jit(tsp.graph)
        end = time.time()
        total6.append(end - start)
    print(f'Media min23jit: {np.mean(total6[1:])}')
