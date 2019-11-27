# -*- coding: utf-8 -*-
import argparse


def test_for_sys(inputfile, outputfile):
    print('the inputfile is', inputfile)
    print('the outputfile is', outputfile)


parser = argparse.ArgumentParser(description='Test for argparse')
parser.add_argument('--inputfile', '-i', help='输入文件', required=True)
parser.add_argument('--outputfile', '-o', help='输出文件', required=True)
args = parser.parse_args()

if __name__ == '__main__':
    print('test')
    #多个输入、输出文件，用‘,’隔开
    try:
        test_for_sys(args.inputfile, args.outputfile)
    except Exception as e:
        print(e)