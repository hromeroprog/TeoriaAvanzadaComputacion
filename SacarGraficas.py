# -*- coding: utf-8 -*-
"""
Created on Fri Feb 12 13:51:10 2021

@author: 2rome
"""

import pandas as pd
import matplotlib.pyplot as plt
df  = pd.read_csv("data.csv")
df.plot()  # plots all columns against index
df.plot(kind='scatter',x='Number',y='Time') # scatter plot
df.plot(kind='density')