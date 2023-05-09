	.text
_test_logical:		# METHOD ENTRY
	sw    $ra, 0($sp)	# PUSH
	subu  $sp, $sp, 4
	sw    $fp, 0($sp)	# PUSH
	subu  $sp, $sp, 4
	addu  $fp, $sp, 8
	subu  $sp, $sp, 0
	li    $t0, 0
	sw    $t0, 0($sp)	# PUSH
	subu  $sp, $sp, 4
	li    $t0, 1
	sw    $t0, 0($sp)	# PUSH
	subu  $sp, $sp, 4
	lw    $t0, 4($sp)	# POP
	addu  $sp, $sp, 4
	li    $t1, 1
	beq   $t0, $t1, .L0
	lw    $t0, 4($sp)	# POP
	addu  $sp, $sp, 4
	beq   $t0, $t1, .L1
	li    $t1, 0
	sw    $t1, 0($sp)	# PUSH
	subu  $sp, $sp, 4
	j     .L2
	sw    $t1, 0($sp)	# PUSH
	subu  $sp, $sp, 4
.L0:
	lw    $t0, 4($sp)	# POP
	addu  $sp, $sp, 4
.L1:
	sw    $t1, 0($sp)	# PUSH
	subu  $sp, $sp, 4
.L2:
	lw    $a0, 4($sp)	# POP
	addu  $sp, $sp, 4
	li    $v0, 1
	syscall
_test_logical_exit:		# FUNCTION EXIT
	lw    $ra, 0($fp)
	move  $t0, $fp		# save control link
	lw    $fp, -4($fp)	# restore FP
	move  $sp, $t0		# restore SP
	jr    $ra
	.text
	.globl main
main:		# METHOD ENTRY
	sw    $ra, 0($sp)	# PUSH
	subu  $sp, $sp, 4
	sw    $fp, 0($sp)	# PUSH
	subu  $sp, $sp, 4
	addu  $fp, $sp, 8
	subu  $sp, $sp, 0
	jal   _test_logical
	li    $t0, 0
	addu  $sp, $sp, $t0
	sw    $v0, 0($sp)	# PUSH
	subu  $sp, $sp, 4
	lw    $t1, 4($sp)	# POP
	addu  $sp, $sp, 4
_main_exit:		# FUNCTION EXIT
	lw    $ra, 0($fp)
	move  $t0, $fp		# save control link
	lw    $fp, -4($fp)	# restore FP
	move  $sp, $t0		# restore SP
	li    $v0, 10		# load exit code for syscall
	syscall		# only do this for main
