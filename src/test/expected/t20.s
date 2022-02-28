		.data
	.align 2
Main:	.space 20
		.text
		.globl main
main:
_L0:
		li $t0, 4
		li $t1, 5
		sgt $s0, $t0, $t1
		move $t0, $s0
		beqz $t0, _L1
		li $t1, 2
		la $t0, Main
		sw $t1, 0($t0)
		b _L0
_L1:
		li.s $f1, 6.0
		la $t0, Main
		s.s $f1, 4($t0)
		jr $ra
		li $t1, 7
		la $t0, Main
		sw $t1, 0($t0)
Main_main3:
_L2:
		li $t0, 4
		li $t1, 5
		sgt $s0, $t0, $t1
		move $t0, $s0
		beqz $t0, _L3
		li $t1, 2
		la $t0, Main
		sw $t1, 0($t0)
		b _L2
_L3:
		li.s $f1, 6.0
		la $t0, Main
		s.s $f1, 12($t0)
		jr $ra
		li $t1, 7
		la $t0, Main
		sw $t1, 0($t0)
