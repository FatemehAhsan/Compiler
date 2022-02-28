		.data
	.align 2
Main:	.space 36
		.text
		.globl main
main:
		li $t1, 2
		la $t0, Main
		sw $t1, 0($t0)
		li.s $f1, 3.0
		la $t0, Main
		s.s $f1, 24($t0)
		li.s $f1, 1.89
		la $t0, Main
		s.s $f1, 28($t0)
		la $t0, Main
		lwc1 $f0, 24($t0)
		la $t0, Main
		lwc1 $f1, 28($t0)
		c.eq.s $f0, $f1
		bc1f _L0
		li $s0 1
		b _L1
_L0:
		li $s0 0
_L1:
		move $t1, $s0
		la $t0, Main
		sw $t1, 32($t0)
		la $t0, Main
		lwc1 $f0, 24($t0)
		la $t0, Main
		lwc1 $f1, 28($t0)
		c.eq.s $f0, $f1
		bc1t _L2
		li $s0 1
		b _L3
_L2:
		li $s0 0
_L3:
		move $t1, $s0
		la $t0, Main
		sw $t1, 32($t0)
		la $t0, Main
		lwc1 $f0, 24($t0)
		la $t0, Main
		lwc1 $f1, 28($t0)
		c.le.s $f0, $f1
		bc1f _L4
		li $s0 1
		b _L5
_L4:
		li $s0 0
_L5:
		move $t1, $s0
		la $t0, Main
		sw $t1, 32($t0)
		la $t0, Main
		lwc1 $f0, 24($t0)
		la $t0, Main
		lwc1 $f1, 28($t0)
		c.lt.s $f0, $f1
		bc1t _L6
		li $s0 1
		b _L7
_L6:
		li $s0 0
_L7:
		move $t1, $s0
		la $t0, Main
		sw $t1, 32($t0)
		la $t0, Main
		lwc1 $f0, 24($t0)
		la $t0, Main
		lwc1 $f1, 28($t0)
		c.lt.s $f0, $f1
		bc1f _L8
		li $s0 1
		b _L9
_L8:
		li $s0 0
_L9:
		move $t1, $s0
		la $t0, Main
		sw $t1, 32($t0)
		la $t0, Main
		lwc1 $f0, 24($t0)
		la $t0, Main
		lwc1 $f1, 28($t0)
		c.le.s $f0, $f1
		bc1t _L10
		li $s0 1
		b _L11
_L10:
		li $s0 0
_L11:
		move $t1, $s0
		la $t0, Main
		sw $t1, 32($t0)
		li $t1, 2
		la $t0, Main
		sw $t1, 16($t0)
		jr $ra
