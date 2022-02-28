		.data
	.align 2
Main:	.space 20
		.text
		.globl main
main:
		li $t1, 2
		la $t0, Main
		sw $t1, 12($t0)
_L0:
		la $t0, Main
		lw $t0, 12($t0)
		li $t1, 3
		slt $s0, $t0, $t1
		move $t0, $s0
		beqz $t0, _L1
		b _L2
_L3:
		b _L0
_L2:
		li $t1, 5
		la $t0, Main
		sw $t1, 16($t0)
		la $t1, Main
		lw $t1, 12($t1)
		addi $t1, $t1, 1
		la $t0, Main
		sw $t1, 12($t0)
		b _L3
_L1:
		li $t1, 2
		la $t0, Main
		sw $t1, 8($t0)
		jr $ra
