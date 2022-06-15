package twilightforest.entity.ai;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import twilightforest.entity.boss.UrGhast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

public class UrGhastFlightGoal extends Goal {

	//private static final int CRUISING_ALTITUDE = 235; // absolute cruising altitude
	private static final int HOVER_ALTITUDE = 20; // how far, relatively, do we hover over ghast traps?
	private final UrGhast ghast;

	private final List<BlockPos> pointsToVisit;
	private int currentPoint = 0;

	public UrGhastFlightGoal(UrGhast ghast) {
		this.ghast = ghast;
		this.pointsToVisit = createPath();
		setFlags(EnumSet.of(Flag.MOVE));
	}

	// [VanillaCopy] Ghast.RandomFloatAroundGoal
	@Override
	public boolean canUse() {
		MoveControl entitymovehelper = this.ghast.getMoveControl();

		if (!entitymovehelper.hasWanted()) {
			return true;
		} else {
			double d0 = entitymovehelper.getWantedX() - this.ghast.getX();
			double d1 = entitymovehelper.getWantedY() - this.ghast.getY();
			double d2 = entitymovehelper.getWantedZ() - this.ghast.getZ();
			double d3 = d0 * d0 + d1 * d1 + d2 * d2;
			return d3 < 1.0D || d3 > 3600.0D;
		}
	}

	@Override
	public boolean canContinueToUse() {
		return false;
	}

	@Override
	public void start() {
		if (this.pointsToVisit.isEmpty()) {
			pointsToVisit.addAll(createPath());
		} else {
			if (this.currentPoint >= pointsToVisit.size()) {
				this.currentPoint = 0;

				// when we're in tantrum mode, this is a good time to check if we need to spawn more ghasts
				if (!ghast.checkGhastsAtTraps()) {
					ghast.spawnGhastsAtTraps();
				}
			}

			// TODO reintroduce wanderFactor somehow? Would need to change move helper or add extra fields here

			double x = pointsToVisit.get(currentPoint).getX();
			double y = pointsToVisit.get(currentPoint).getY() + HOVER_ALTITUDE;
			double z = pointsToVisit.get(currentPoint).getZ();
			ghast.getMoveControl().setWantedPosition(x, y, z, 1.0F);
			this.currentPoint++;

			// we have reached cruising altitude, time to turn noClip off
			ghast.noPhysics = false;
		}
	}

	private List<BlockPos> createPath() {
		List<BlockPos> potentialPoints = new ArrayList<>();
		BlockPos pos = new BlockPos(this.ghast.blockPosition());

		if (!this.ghast.isInNoTrapMode()) {
			// make a copy of the trap locations list
			potentialPoints.addAll(this.ghast.getTrapLocations());
		} else {
			potentialPoints.add(pos.offset(20, -HOVER_ALTITUDE, 0));
			potentialPoints.add(pos.offset(0, -HOVER_ALTITUDE, -20));
			potentialPoints.add(pos.offset(-20, -HOVER_ALTITUDE, 0));
			potentialPoints.add(pos.offset(0, -HOVER_ALTITUDE, 20));
		}

		Collections.shuffle(potentialPoints);

		if (this.ghast.isInNoTrapMode()) {
			// if in no trap mode, head back to the middle when we're done
			potentialPoints.add(pos.below(HOVER_ALTITUDE));
		}

		return potentialPoints;
	}
}