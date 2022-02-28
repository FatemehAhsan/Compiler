		.data
	.align 2
Main:	.space 12
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
		sw $t1, 8($t0)
		b _L0
_L1:
		li $t1, 2
		la $t0, Main
		sw $t1, 4($t0)
		jr $ra
