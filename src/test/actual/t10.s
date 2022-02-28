		.data
	.align 2
Main:	.space 12
		.text
		.globl main
main:
		li $t0, 3
		li $t1, 4
		sgt $s0, $t0, $t1
		move $t0, $s0
		beqz $t0, _L0
		li $t1, 7
		la $t0, Main
		sw $t1, 8($t0)
		b _L1
_L0:
		li $t1, 5
		la $t0, Main
		sw $t1, 8($t0)
_L1:
		li $t1, 2
		la $t0, Main
		sw $t1, 4($t0)
		jr $ra
